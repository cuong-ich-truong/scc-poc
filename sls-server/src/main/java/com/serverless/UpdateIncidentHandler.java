package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.dal.Incident;
import com.serverless.dto.UpdateIncidentRequest;
import com.serverless.service.PushNotificationService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Map;

import static com.serverless.utils.RequestUtils.getPathVariable;
import static com.serverless.utils.RequestUtils.getRequestBody;

public class UpdateIncidentHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {

            // update incident
            Incident updatedIncident = new Incident().update(getPathVariable(input, "incidentId"), getRequestBody(input, UpdateIncidentRequest.class));

            // send notification to guard
            if (!updatedIncident.isIgnored() && updatedIncident.isAssignedToGuard()) {
                new PushNotificationService().sendAlert(updatedIncident);
            }

            // send the response back
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(updatedIncident)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                    .enableCors()
                    .build();
        } catch (Exception ex) {
            logger.error("Error in updating incident: " + ex);

            // send the error response back
            Response responseBody = new Response("Error in updating incident: ", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody(responseBody)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                    .enableCors()
                    .build();
        }
    }
}
