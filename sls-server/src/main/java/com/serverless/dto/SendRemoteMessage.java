package com.serverless.dto;

import java.util.Map;
import java.util.HashMap;

public class SendRemoteMessage {

  private String body;
  private String title;
  private Map<String, String> data = new HashMap<String, String>();

  public SendRemoteMessage(String title, String body, String incidentId) {
    this.title = title;
    this.body = "New incident detected at your premises.";
    this.data.put("incident_id", incidentId);
  }

  public String toString() {
    String json = "{ \"notification\": { \"body\": \"" + this.body + "\", \"title\": \"" + this.title
        + "\" }, \"data\": { \"incident_id\": \"" + this.data.get("incident_id") + "\" } }";
    return "{ \"GCM\": \"" + json + "\" }";
  }
}
