package com.serverless.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateIncidentPathVariable {
    @JsonProperty("incidentId")
    public String incidentId;
}
