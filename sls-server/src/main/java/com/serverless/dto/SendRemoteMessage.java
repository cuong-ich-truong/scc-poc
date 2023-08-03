package com.serverless.dto;

import org.json.JSONObject;
import java.util.Map;
import java.util.HashMap;

public class SendRemoteMessage implements java.io.Serializable {

  public String title;
  public String body;
  Map<String, String> data = new HashMap<String, String>();

  public SendRemoteMessage(String title, String body, String incidentId) {
    this.title = title;
    this.body = body;
    this.data.put("incident_id", incidentId);
  }

  public String toJsonMessage() {
    // format: {"GCM":"{ \"notification\": { \"body\": \"test body\", \"title\":\"TitleTest 2\" }, \"data\": { \"incidentId\": \"12334345\"} }"}
    JSONObject incidentObj = new JSONObject();  
    incidentObj.put("incident_id", this.data.get("incident_id"));

    JSONObject notification = new JSONObject();
    notification.put("body", this.body);
    notification.put("title", "");

    JSONObject gcm = new JSONObject();
    gcm.put("notification", notification);
    gcm.put("data", incidentObj);

    JSONObject obj = new JSONObject();
    obj.put("GCM", gcm.toString());

    return obj.toString();
  }
}
