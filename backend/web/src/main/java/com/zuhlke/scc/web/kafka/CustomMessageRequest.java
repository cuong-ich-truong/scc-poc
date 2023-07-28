package com.zuhlke.scc.web.kafka;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CustomMessageRequest {

    private String username;
    private String password;
}
