package com.serverless.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

import static com.serverless.utils.Constants.BODY;
import static com.serverless.utils.Constants.PATH_PARAMETERS;

public class RequestUtils {
    public static String getPathVariable(Map<String, Object> request, String key) {
        Map<String, String> pathParameters = (Map<String, String>) request.get(PATH_PARAMETERS);
        return pathParameters.get(key);
    }

    public static <T> T getRequestBody(Map<String, Object> request, Class<T> class_) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(String.valueOf(request.get(BODY)), class_);
    }
}
