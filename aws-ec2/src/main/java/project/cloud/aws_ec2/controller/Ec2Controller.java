package project.cloud.aws_ec2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ec2")
public class Ec2Controller {

    private final Ec2Client ec2Client;

    public Ec2Controller() {
        this.ec2Client = Ec2Client.builder()
                .region(Region.AP_SOUTH_1) // Change to your AWS region
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    @GetMapping("/instances")
    public List<String> getEc2Instances() {
        DescribeInstancesResponse response = ec2Client.describeInstances();
        
        return response.reservations().stream()
                .flatMap(reservation -> reservation.instances().stream())
                .map(instance -> "Instance ID: " + instance.instanceId() +
                        ", State: " + instance.state().name() +
                        ", Public IP: " + (instance.publicIpAddress() != null ? instance.publicIpAddress() : "N/A"))
                .collect(Collectors.toList());
    }
}
