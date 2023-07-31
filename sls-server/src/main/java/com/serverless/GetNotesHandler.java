package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.dal.Note;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GetNotesHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {
            // get all premises
            List<Note> notes = new Note().list();

            // send the response back
            return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(notes)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
        } catch (Exception ex) {
            logger.error("Error in listing notes: " + ex);

            // send the error response back
            Response responseBody = new Response("Error in listing notes: ", input);
            return ApiGatewayResponse.builder()
                .setStatusCode(500)
                .setObjectBody(responseBody)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
        }
    }
}