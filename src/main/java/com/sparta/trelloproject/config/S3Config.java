package com.sparta.trelloproject.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
  // AWS
  private final String accessKey = "AKIATQZCSKGDHVOKXJ4O";
  private final String secretKey = "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";
  private final String region = "ap-northeast-2"; // 예: "us-west-2"

  @Bean
  public AmazonS3 amazonS3() {
    BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
    return AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
        .withRegion(region)
        .build();
  }
}
