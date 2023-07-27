package com.zuhlke.scc.web.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IncidentRequest {

    private String name;
}
