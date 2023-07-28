package com.zuhlke.scc.web.domain.incident.serverlesshandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.zuhlke.scc.web.serverless.ApiGatewayResponse;
import java.util.Map;

public class GetAllIncidentsHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> stringObjectMap, Context context) {
        return null;
    }
}
