package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.dal.Guard;
import com.serverless.dto.UpdateGuardTokenRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Map;

import static com.serverless.utils.RequestUtils.getPathVariable;
import static com.serverless.utils.RequestUtils.getRequestBody;

public class UpdateGuardTokenHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {

            for (String key : input.keySet()) {
                logger.info(String.format("Key: %s | value: %s", key, input.get(key)));
            }

            // update guard token
            Guard updatedGuard = new Guard().updateToken(getPathVariable(input, "guardId"),
                    getPathVariable(input, "token"));

            // send the response back
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(updatedGuard)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                    .build();
        } catch (Exception ex) {
            logger.error("Error in updating guard: " + ex);

            // send the error response back
            Response responseBody = new Response("Error in updating guard: ", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody(responseBody)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                    .build();
        }
    }
}
