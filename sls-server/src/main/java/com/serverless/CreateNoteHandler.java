package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.common.FileType;
import com.serverless.dal.Note;
import com.serverless.dto.CreateNoteRequestBody;
import com.serverless.dto.CreateNoteResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.Collections;
import java.util.Map;

public class CreateNoteHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private Logger logger = LogManager.getLogger(this.getClass());

    private static final String PATH_PARAMETERS = "pathParameters";
    private static final String BODY = "body";

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {
            Map<String, String> pathParameters = (Map<String, String>) input.get(PATH_PARAMETERS);
            String incidentId = pathParameters.get("incidentId");
            ObjectMapper mapper = new ObjectMapper();

            CreateNoteRequestBody createNoteRequestBody = mapper.readValue(String.valueOf(input.get(BODY)), CreateNoteRequestBody.class);

            Note newNote = new Note().create(incidentId, createNoteRequestBody.getDescription());
            String noteId = newNote.getId();
            logger.info(String.format("Note [id=%s, incidentId=%s]", noteId, incidentId));

            URL presignedUrlVideo = new Note().putPresignedURL(incidentId, noteId, FileType.VIDEO);
            URL presignedUrlPicture = new Note().putPresignedURL(incidentId, noteId, FileType.PICTURE);

            CreateNoteResponseBody createNoteResponseBody = new CreateNoteResponseBody();
            createNoteResponseBody.setDescription(newNote.getDescription());
            createNoteResponseBody.setPictureUrl(presignedUrlPicture);
            createNoteResponseBody.setVideoUrl(presignedUrlVideo);
            createNoteResponseBody.setId(noteId);

            return ApiGatewayResponse.builder()
                .setStatusCode(201)
                .setObjectBody(createNoteResponseBody)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();

        } catch (Exception ex) {
            logger.error("Error in attaching note: " + ex);

            // send the error response back
            Response responseBody = new Response("Error in attaching note: ", input);
            return ApiGatewayResponse.builder()
                .setStatusCode(500)
                .setObjectBody(responseBody)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
        }
    }
}


