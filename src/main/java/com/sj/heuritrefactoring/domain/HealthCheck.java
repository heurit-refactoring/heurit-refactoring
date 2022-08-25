package com.sj.heuritrefactoring.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/ping")
    public String healthCheck() {

        return "pong";
    }
}
