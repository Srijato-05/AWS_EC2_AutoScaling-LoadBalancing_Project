package com.example.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CloudController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Welcome to my first AWS deployment!";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }
    
    @GetMapping("/env")
    public String environmentCheck() {
        return "Running on AWS";
    }
}
