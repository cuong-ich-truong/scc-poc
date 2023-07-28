package com.zuhlke.scc.web.serverless;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@Builder
@RequiredArgsConstructor
public class Response {

    private final String message;
    private final Map<String, Object> input;
}
