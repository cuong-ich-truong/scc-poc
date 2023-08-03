package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.dal.Incident;
import com.serverless.dal.Note;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GetNotesOfIncidentHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {
            Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
            String incidentId = pathParameters.get("incidentId");

            List<Note> notes = new Note().listNotesByIncidentId(incidentId);

            return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(notes)
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
