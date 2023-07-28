package com.zuhlke.scc.web.domain.incident;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IncidentRequest {

    private String name;
}
