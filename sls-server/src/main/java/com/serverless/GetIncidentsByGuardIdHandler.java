package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.dal.Incident;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GetIncidentsByGuardIdHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private Logger logger = LogManager.getLogger(this.getClass());

    static final String QUERY_PARAM = "queryStringParameters";
    static final String GUARD_ID_PARAM = "guardId";

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {
            Map<String, String> queryParameters = (Map<String, String>) input.get(QUERY_PARAM);
            List<Incident> incidents;

            if (queryParameters != null && queryParameters.containsKey(GUARD_ID_PARAM)) {
                String guardId = queryParameters.get(GUARD_ID_PARAM);
                incidents = new Incident().getIncidentsByGuardId(guardId);
            } else {
                incidents = new Incident().list();
            }

            // send the response back
            return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(incidents)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
        } catch (Exception ex) {
            logger.error("Error in getting incidents: " + ex);

            // send the error response back
            Response responseBody = new Response("Error in getting incidents: ", input);
            return ApiGatewayResponse.builder()
                .setStatusCode(500)
                .setObjectBody(responseBody)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
        }
    }
}
