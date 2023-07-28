package com.zuhlke.scc.web.kafka;

import lombok.Data;

@Data
public class CustomMessageRequest {

    private String username;
    private String password;
}
