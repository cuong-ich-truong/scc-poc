package com.serverless.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateAlertMessageEvent {
    @JsonProperty("name")
    public String name;
    @JsonProperty("cameraId")
    public String cameraId;
    @JsonProperty("dateCreated")
    public String dateCreated;

    public String toString() {
        String json = "{ \"name\": \"" + this.name + "\", \"cameraId\": \"" + this.cameraId + "\", \"dateCreated\": \""
                + this.dateCreated + "\" }";
        return json;
    }
}
