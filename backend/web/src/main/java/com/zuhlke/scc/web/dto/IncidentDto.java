package com.zuhlke.scc.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncidentDto {
    private String id;
    private String name;
}
