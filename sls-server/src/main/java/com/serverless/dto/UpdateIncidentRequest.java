package com.serverless.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateIncidentRequest {
    @JsonProperty("guardId")
    public String guardId;
    @JsonProperty("ignore")
    public Boolean ignore;

    public String getGuardId() {
        return guardId;
    }

    public void setGuardId(String guardId) {
        this.guardId = guardId;
    }

    public Boolean getIgnore() {
        return ignore;
    }

    public void setIgnore(Boolean ignore) {
        this.ignore = ignore;
    }
}
