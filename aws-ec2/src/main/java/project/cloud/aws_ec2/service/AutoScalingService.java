package project.cloud.aws_ec2.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.autoscaling.AutoScalingClient;
import software.amazon.awssdk.services.autoscaling.model.*;

@Service
public class AutoScalingService {
    private final AutoScalingClient autoScalingClient;

    public AutoScalingService() {
        this.autoScalingClient = AutoScalingClient.create();
    }

    public String createAutoScalingGroup() {
        CreateAutoScalingGroupRequest request = CreateAutoScalingGroupRequest.builder()
                .autoScalingGroupName("MyAutoScalingGroup")
                .instanceId("ami-12345678") // Change to your AMI
                .minSize(1)
                .maxSize(5)
                .desiredCapacity(2)
                .vpcZoneIdentifier("subnet-abcdef12") // Change this to your subnet
                .build();

        autoScalingClient.createAutoScalingGroup(request);
        return "Auto Scaling Group Created!";
    }
}
