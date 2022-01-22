package com.ideas.aws.sns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@RestController
@SpringBootApplication
public class AwsSnsTestingApplication {

    //XXXXXXXXXXXX : 12-digit aws account number
    private static final String TOPIC_ARN = "arn:aws:sns:us-east-2:XXXXXXXXXXXX:TestTopic";
    private static final String EMAIL_MESSAGE = "Successfully Triggered Message from SNS";

    @GetMapping("/first-api")
    public String welcome() {

        SnsClient snsClient = SnsClient.builder().region(Region.US_EAST_2).build();

        PublishRequest request = PublishRequest.builder().message(EMAIL_MESSAGE).topicArn(TOPIC_ARN).build();
        PublishResponse response = snsClient.publish(request);
        return response.messageId();
    }

    public static void main(String[] args) {
        SpringApplication.run(AwsSnsTestingApplication.class, args);
    }

}
