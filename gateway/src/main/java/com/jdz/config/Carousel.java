package com.jdz.config;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class Carousel {
    private final EurekaClient eurekaClient;
    List<InstanceInfo> instanceInfoList = new ArrayList<>();
    int currentIndex = 0;

    public Carousel(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
        try {
            initAuthCarousel();
        } catch (NullPointerException e) {
            log.warn("[Carousel] cant find active instances of Auth Service");
//            e.printStackTrace();
        }
        events();
    }

    public String getUriAuth() {
        StringBuilder sb = new StringBuilder();
        InstanceInfo instance = instanceInfoList.get(currentIndex);
        sb.append(instance.getIPAddr())
                .append(":")
                .append(instance.getPort());
        if (instanceInfoList.size() - 1 == currentIndex) {
            currentIndex = 0;
        } else {
            currentIndex++;
        }
        return sb.toString();
    }

    private void events() {
        eurekaClient.registerEventListener(eurekaEvent -> {
            try {
                initAuthCarousel();
            } catch (NullPointerException e) {
                log.warn("[Carousel] cant find active instances of Auth Service");
            }
        });
    }

    private void initAuthCarousel() {
        instanceInfoList = eurekaClient.getApplication("AUTH-SERVICE").getInstances();
    }
}
