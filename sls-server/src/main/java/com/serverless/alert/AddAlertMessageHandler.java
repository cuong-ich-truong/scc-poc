package com.serverless.alert;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.ApiGatewayResponse;
import com.serverless.Response;
import com.serverless.dto.CreateAlertMessageEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Map;

import static com.serverless.utils.RequestUtils.getRequestBody;

public class AddAlertMessageHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

  private final Logger logger = LogManager.getLogger(this.getClass());

  @Override
  public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
    try {

      CreateAlertMessageEvent alertMessage = getRequestBody(input, CreateAlertMessageEvent.class);

      AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
      String queueUrl = System.getenv("SQS_ALERTS_QUEUE_URL");
      SendMessageRequest send_msg_request = new SendMessageRequest()
        .withQueueUrl(queueUrl)
        .withMessageBody(alertMessage.toString())
        .withDelaySeconds(5);
      SendMessageResult result = sqs.sendMessage(send_msg_request);
      

      // send the response back
      return ApiGatewayResponse.builder()
          .setStatusCode(200)
          .setObjectBody(result.getMessageId() + " Message sent.")
          .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
          .build();
    } catch (Exception ex) {
      logger.error("Error in adding alert to SQS: " + ex);

      // send the error response back
      Response responseBody = new Response("Error in adding alert to SQS: ", input);
      return ApiGatewayResponse.builder()
          .setStatusCode(500)
          .setObjectBody(responseBody)
          .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
          .build();
    }
  }
}