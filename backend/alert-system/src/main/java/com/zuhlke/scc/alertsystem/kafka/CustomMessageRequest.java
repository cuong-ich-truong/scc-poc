package com.zuhlke.scc.alertsystem.kafka;

import lombok.Data;

@Data
public class CustomMessageRequest {

    private String username;
    private String password;
}
