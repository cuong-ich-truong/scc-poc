package com.serverless.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateIncidentRequest {
    @JsonProperty("guardId")
    public String guardId;
    @JsonProperty("ignore")
    public Boolean ignore;
}
