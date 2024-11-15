package com.smartosc.springcloudconfigclient.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class MyService {

    private final RestTemplate restTemplate;

    public MyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "myService", fallbackMethod = "fallbackMethod")
    public String getDataFromExternalService() {
        return restTemplate.getForObject("https://randomuser.me/api/1", String.class);
    }

    @Bean
    public String fallbackMethod(Throwable t) {
        return "Fallback response: Service is currently unavailable.";
    }
}