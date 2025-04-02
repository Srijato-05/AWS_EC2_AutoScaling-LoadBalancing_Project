package project.cloud.aws_ec2.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.elasticloadbalancingv2.ElasticLoadBalancingV2Client;

@Service
public class LoadBalancerService {

    private final ElasticLoadBalancingV2Client elbClient;

    public LoadBalancerService() {
        //Define the AWS Region (Change as needed)
        Region region = Region.of(System.getenv().getOrDefault("AWS_REGION", "ap-south-1")); 

        // Initialize ELB Client
        this.elbClient = ElasticLoadBalancingV2Client.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create()) // Uses default AWS credentials
                .build();
    }

    public ElasticLoadBalancingV2Client getElbClient() {
        return elbClient;
    }
}
