package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.dal.Camera;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Map;
import java.util.List;

public class GetCamerasHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {
            // get all cameras
            List<Camera> cameras = new Camera().list();

            // send the response back
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(cameras)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                    .build();
        } catch (Exception ex) {
            logger.error("Error in listing cameras: " + ex);

            // send the error response back
            Response responseBody = new Response("Error in listing cameras: ", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody(responseBody)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                    .build();
        }
    }
}
