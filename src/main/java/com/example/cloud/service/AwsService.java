package com.example.cloud.service;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.autoscaling.AutoScalingClient;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.elasticloadbalancingv2.ElasticLoadBalancingV2Client;

@Service
public class AwsService {
    
    private final Ec2Client ec2Client;
    private final AutoScalingClient autoScalingClient;
    private final ElasticLoadBalancingV2Client elbClient;

    public AwsService(Ec2Client ec2Client, AutoScalingClient autoScalingClient, ElasticLoadBalancingV2Client elbClient) {
        this.ec2Client = ec2Client;
        this.autoScalingClient = autoScalingClient;
        this.elbClient = elbClient;
    }

    public String listInstances() {
        return ec2Client.describeInstances().reservations().toString();
    }

    public String listAutoScalingGroups() {
        return autoScalingClient.describeAutoScalingGroups().autoScalingGroups().toString();
    }

    public String listLoadBalancers() {
        return elbClient.describeLoadBalancers().loadBalancers().toString();
    }
}
