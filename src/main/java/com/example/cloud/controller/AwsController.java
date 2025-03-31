package com.example.cloud.controller;

import com.example.cloud.service.AwsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aws")
public class AwsController {
    
    private final AwsService awsService;

    public AwsController(AwsService awsService) {
        this.awsService = awsService;
    }

    @GetMapping("/instances")
    public String getInstances() {
        return awsService.listInstances();
    }

    @GetMapping("/autoscaling")
    public String getAutoScalingGroups() {
        return awsService.listAutoScalingGroups();
    }

    @GetMapping("/loadbalancers")
    public String getLoadBalancers() {
        return awsService.listLoadBalancers();
    }
}
