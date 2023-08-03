package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.dal.Incident;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.serverless.utils.Constants.INCIDENT_IDS_SEPARATOR;
import static com.serverless.utils.Constants.QUERY_PARAM;

public class GetNewIncidentsHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    static final String INCIDENT_IDS_PARAM = "incidentIds";

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {
            Map<String, String> queryParameters = (Map<String, String>) input.get(QUERY_PARAM);
            List<Incident> incidents;

            if (queryParameters != null && queryParameters.containsKey(INCIDENT_IDS_PARAM)) {
                String[] incidentIds = StringUtils.split(queryParameters.get(INCIDENT_IDS_PARAM), INCIDENT_IDS_SEPARATOR);
                incidents = new Incident().getIncidentsExcludedIds(incidentIds);
            } else {
                incidents = new Incident().list();
            }

            // send the response back
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(incidents)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                    .enableCors()
                    .build();
        } catch (Exception ex) {
            logger.error("Error in getting incidents: " + ex);

            // send the error response back
            Response responseBody = new Response("Error in getting incidents: ", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody(responseBody)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                    .enableCors()
                    .build();
        }
    }
}
