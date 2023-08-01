package com.serverless.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateGuardTokenRequest {
    @JsonProperty("token")
    public String token;
}
