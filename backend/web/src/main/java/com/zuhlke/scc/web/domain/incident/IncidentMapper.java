package com.zuhlke.scc.web.domain.incident;

import com.zuhlke.scc.web.domain.incident.IncidentEntity;
import com.zuhlke.scc.web.domain.incident.IncidentDto;
import org.mapstruct.Mapper;

@Mapper
public interface IncidentMapper {

    IncidentDto toIncidentDto(IncidentEntity incidentEntity);

//    default IncidentEntity toIncidentEntity(IncidentDto incidentDto) {
//        return IncidentEntity.builder()
//            .id(incidentDto.getId())
//            .name(incidentDto.getName())
//            .build();
//    }

    IncidentEntity toIncidentEntity(IncidentDto incidentDto);
}
