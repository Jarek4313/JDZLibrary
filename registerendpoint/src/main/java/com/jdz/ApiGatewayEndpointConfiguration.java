package com.jdz;

import java.util.ArrayList;
import java.util.List;

public interface ApiGatewayEndpointConfiguration {
    List<Endpoint> endpointList = new ArrayList<>();

    void initMap();
    void register();
}
