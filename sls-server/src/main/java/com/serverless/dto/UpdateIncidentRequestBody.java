package com.serverless.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateIncidentRequestBody {
    @JsonProperty("guardId")
    public String guardId;
    @JsonProperty("ignore")
    public Boolean ignore;
}
