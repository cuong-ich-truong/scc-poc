package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.dal.Note;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Map;

public class CreateNoteHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {
            // Get the body from the input and cast to String
            String body = (String) input.get("body");

            // Use Jackson (or similar) to convert JSON string to Note object
            ObjectMapper mapper = new ObjectMapper();
            Note note = mapper.readValue(body, Note.class);

            logger.info("Note request " + note);

            Note createdNote = new Note().create(note);

            return ApiGatewayResponse.builder()
                .setStatusCode(201)
                .setObjectBody(createdNote)
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


