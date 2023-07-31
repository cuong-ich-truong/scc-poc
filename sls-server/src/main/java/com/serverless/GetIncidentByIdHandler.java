package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.dal.Incident;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Map;

import static com.serverless.utils.RequestUtils.getPathVariable;

public class GetIncidentByIdHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {
            Incident incident = new Incident().get(getPathVariable(input, "incidentId"));

            // send the response back
            return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(incident)
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
