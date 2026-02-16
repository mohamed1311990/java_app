package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloApplication {

    @Value("${APP_NAME:UNKNOWN}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

    @GetMapping("/api/hello")
    public String apiHello() {
        return "Hello from " + appName + " API endpoint!";
    }

    @GetMapping("/web/hello")
    public String webHello() {
        return "Hello from " + appName + " WEB endpoint!";
    }
}
