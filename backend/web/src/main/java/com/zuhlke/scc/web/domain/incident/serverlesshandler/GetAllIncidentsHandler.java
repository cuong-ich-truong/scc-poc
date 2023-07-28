package com.zuhlke.scc.web.domain.incident.serverlesshandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.zuhlke.scc.web.domain.incident.IncidentDto;
import com.zuhlke.scc.web.domain.incident.IncidentService;
import com.zuhlke.scc.web.serverless.ApiGatewayResponse;
import com.zuhlke.scc.web.serverless.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class GetAllIncidentsHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private final IncidentService incidentService;

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {
            List<IncidentDto> incidents = incidentService.getAllIncidents();

            // send the response back
            return ApiGatewayResponse.builder()
                .setStatusCode(200)
                .setObjectBody(incidents)
                .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                .build();
        } catch (Exception ex) {
            log.error("Error in listing premises: " + ex);

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
