package project.cloud.aws_ec2.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

import java.util.Collections;
import java.util.List;

@Service
public class Ec2Service {
    private final Ec2Client ec2Client;

    public Ec2Service() {
        this.ec2Client = Ec2Client.builder()
                .region(Region.AP_SOUTH_1) // Change to your region
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }

    // 🚀 Create a new EC2 instance
    public String createInstance() {
        RunInstancesRequest runRequest = RunInstancesRequest.builder()
                .imageId("ami-0e35ddab05955cf57") // Change this to a real AMI ID
                .instanceType(InstanceType.T2_MICRO)
                .maxCount(1)
                .minCount(1)
                .keyName("ec2_project-key-pair") // Change this to your AWS key pair
                .securityGroups("default") // Change this to your security group
                .build();

        RunInstancesResponse response = ec2Client.runInstances(runRequest);
        String instanceId = response.instances().get(0).instanceId();

        // Tag the instance for better tracking
        Tag tag = Tag.builder().key("Name").value("Auto-Scaled-Instance").build();
        CreateTagsRequest tagRequest = CreateTagsRequest.builder()
                .resources(instanceId)
                .tags(tag)
                .build();
        ec2Client.createTags(tagRequest);

        return "EC2 Instance Created with ID: " + instanceId;
    }
}
