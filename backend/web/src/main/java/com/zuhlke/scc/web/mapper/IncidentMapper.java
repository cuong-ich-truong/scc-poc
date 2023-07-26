package com.zuhlke.scc.web.mapper;

import com.zuhlke.scc.web.db.entity.IncidentEntity;
import com.zuhlke.scc.web.dto.IncidentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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
