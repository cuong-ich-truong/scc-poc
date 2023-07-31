package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.dal.Incident;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Map;

public class CreateIncidentHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {
            // Get the body from the input and cast to String
            String body = (String) input.get("body");

            // Use Jackson (or similar) to convert JSON string to Incident object
            ObjectMapper mapper = new ObjectMapper();
            Incident incident = mapper.readValue(body, Incident.class);

            logger.info("Incident request " + incident);

            Incident createdIncident = new Incident().create(incident);

            return ApiGatewayResponse.builder()
                .setStatusCode(201)
                .setObjectBody(createdIncident)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();

        } catch (Exception ex) {
            logger.error("Error in getting incident: " + ex);

            // send the error response back
            Response responseBody = new Response("Error in listing premises: ", input);
            return ApiGatewayResponse.builder()
                .setStatusCode(500)
                .setObjectBody(responseBody)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
        }
    }
}

