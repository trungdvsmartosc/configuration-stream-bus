package com.smartosc.springcloudconfigclient.controller;

import com.smartosc.springcloudconfigclient.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class DemoController {

    @Value("${message}")
    private String message;

    @GetMapping("/dev")
    public String getProperties() {
        System.out.println(message);
        return message;
    }

    @Autowired
    private MyService myService;

    @GetMapping("/external-data")
    public String getExternalData() {
        return myService.getDataFromExternalService();
    }
}
