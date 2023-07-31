package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.dal.Incident;
import com.serverless.dto.UpdateIncidentPathVariable;
import com.serverless.dto.UpdateIncidentRequestBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Map;

public class UpdateIncidentHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    public static final String PATH_PARAMETERS = "pathParameters";
    public static final String BODY = "body";
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            for (String key : input.keySet()) {
                logger.info(String.format("Key: %s | value: %s", key, input.get(key)));
            }

            // convertValue for pathParameters type Object
            UpdateIncidentPathVariable updateIncidentPathVariable = mapper.convertValue(input.get(PATH_PARAMETERS), UpdateIncidentPathVariable.class);

            // readValue for body type String
            UpdateIncidentRequestBody updateIncidentRequestBody = mapper.readValue(String.valueOf(input.get(BODY)), UpdateIncidentRequestBody.class);

            // update camera name
            Incident updatedCamera = new Incident().update(updateIncidentPathVariable.incidentId, updateIncidentRequestBody);

            // send the response back
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(updatedCamera)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                    .build();
        } catch (Exception ex) {
            logger.error("Error in updating incident: " + ex);

            // send the error response back
            Response responseBody = new Response("Error in updating incident: ", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody(responseBody)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                    .build();
        }
    }
}
