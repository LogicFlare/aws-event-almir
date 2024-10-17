package api.lg.aws.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Value("${aws.region}")
    private String awsRegion;

    // qualquer coisa volta no minuto 47 do video

    @Bean
    public AmazonS3 createS3Instance() {
        return AmazonS3ClientBuilder.standard().withRegion(awsRegion).build();
    }

}
