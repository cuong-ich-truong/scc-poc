package com.serverless.service;

import com.serverless.dal.Guard;
import com.serverless.dal.Incident;
import com.serverless.dto.SendRemoteMessage;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.CreatePlatformEndpointRequest;
import software.amazon.awssdk.services.sns.model.CreatePlatformEndpointResponse;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PushNotificationService {
  
  private final Logger logger = LogManager.getLogger(this.getClass());

  public String sendAlert(Incident incident) {
    try {
      if (incident.isIgnored() || incident.getGuardId() == null) {
        return null;
      }
      logger.info("incident " + incident.toString());
      Guard guard = new Guard().get(incident.getGuardId());

      SendRemoteMessage message = new SendRemoteMessage("New Incident", incident.getName(), incident.getId());
      logger.info("message " + message.toString());

      SnsClient snsClient = SnsClient.builder()
          .region(Region.US_EAST_1)
          .credentialsProvider(ProfileCredentialsProvider.create())
          .build();

      String platformApplicationArn = System.getenv("SNS_PLATFORM_APPLICATION_ARN");
      CreatePlatformEndpointRequest endpointRequest = CreatePlatformEndpointRequest.builder()
          .token(guard.getToken())
          .customUserData(incident.getGuardId())
          .platformApplicationArn(platformApplicationArn)
          .build();

      // The createPlatformEndpoint action is idempotent, the same token wil result in
      // the same EndpointArn
      CreatePlatformEndpointResponse response = snsClient.createPlatformEndpoint(endpointRequest);

      PublishRequest request = PublishRequest.builder()
          .message(message.toString())
          .targetArn(response.endpointArn())
          .build();

      PublishResponse result = snsClient.publish(request);
      logger.info("Push notification sent to " + incident.getGuardId());
      return result.messageId();
    } catch (Exception e) {
      logger.error("Error sending push notification to " + incident.getGuardId());
      logger.error(e.toString());
      return null;
    }
  }
}
