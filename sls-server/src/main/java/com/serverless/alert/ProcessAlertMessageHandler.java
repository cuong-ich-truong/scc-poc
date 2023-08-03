package com.serverless.alert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.dal.Incident;
import com.serverless.dto.CreateAlertMessageEvent;

public class ProcessAlertMessageHandler implements RequestHandler<SQSEvent, Void> {

  private final Logger logger = LogManager.getLogger(this.getClass());

  @Override
  public Void handleRequest(SQSEvent event, Context context) {
    try {
      for(SQSMessage msg : event.getRecords()){
        
        String msgBody = msg.getBody();
        ObjectMapper mapper = new ObjectMapper();
        
        Incident incident = mapper.readValue(msgBody, Incident.class);
        Incident createdIncident = new Incident().create(incident);
        logger.info("Incident created " + createdIncident);
    }

    } catch (Exception ex) {
      logger.error("Error in processing alert message: " + ex);
    }

    return null;
  }
}