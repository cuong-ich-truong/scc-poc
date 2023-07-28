package com.zuhlke.scc.web.domain.incident;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncidentDto {
    private String id;
    private String name;
}
