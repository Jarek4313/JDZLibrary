package com.jdz.controller;

import com.jdz.Endpoint;
import com.jdz.Response;
import com.jdz.filter.RouteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/gateway")
public class RegistrationController {
    private final RouteValidator routeValidator;

    @PostMapping
    public ResponseEntity<Response> register(@RequestBody List<Endpoint> endpoints) {
        routeValidator.addEndpoints(endpoints);
        return ResponseEntity.ok(new Response("Successfully register new endpoints"));
    }
}
