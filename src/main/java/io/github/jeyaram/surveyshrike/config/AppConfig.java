package io.github.jeyaram.surveyshrike.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Value("${amazon.s3.accessKey}")
    private String accessKey;

    @Value("${amazon.s3.secretKey}")
    private String secretKey;

    @Bean
    public AWSCredentials credentials() {
        return new BasicAWSCredentials(
                accessKey,
                secretKey
        );
    }

    @Bean
    public AmazonS3 s3Client(AWSCredentials credentials) {
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTH_1)
                .build();

        return s3client;
    }
}
