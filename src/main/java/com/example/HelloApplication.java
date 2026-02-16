package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@SpringBootApplication
@RestController
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

    // Good for a basic check (and for k8s readiness/liveness if you want)
    @GetMapping("/")
    public String root() {
        return "OK - root";
    }

    // Endpoint you can route with an ingress rule like /api -> this service
    @GetMapping("/api/hello")
    public Map<String, Object> apiHello() {
        return Map.of(
                "message", "Hello from /api/hello",
                "time", Instant.now().toString()
        );
    }

    // Another endpoint to test a second rule like /web -> this service
    @GetMapping("/web/hello")
    public String webHello() {
        return "Hello from /web/hello";
    }

    // Useful to confirm which headers ingress adds (Host, X-Forwarded-For, etc.)
    @GetMapping("/api/headers")
    public ResponseEntity<Map<String, Object>> headers(
            @RequestHeader Map<String, String> headers
    ) {
        return ResponseEntity.ok(Map.of(
                "message", "Headers received",
                "headers", headers
        ));
    }

    // Health endpoint (optional, but very useful in Kubernetes)
    @GetMapping("/actuator/healthz")
    public Map<String, Object> healthz() {
        return Map.of("status", "UP");
    }
}
