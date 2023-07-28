package com.zuhlke.scc.web.mapper;

import com.zuhlke.scc.web.db.entity.IncidentEntity;
import com.zuhlke.scc.web.dto.IncidentDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-26T12:20:32+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
public class IncidentMapperImpl implements IncidentMapper {

    @Override
    public IncidentDto toIncidentDto(IncidentEntity incidentEntity) {
        if ( incidentEntity == null ) {
            return null;
        }

        IncidentDto incidentDto = new IncidentDto();

        incidentDto.setId( incidentEntity.getId() );
        incidentDto.setName( incidentEntity.getName() );

        return incidentDto;
    }

    @Override
    public IncidentEntity toIncidentEntity(IncidentDto incidentDto) {
        if ( incidentDto == null ) {
            return null;
        }

        IncidentEntity incidentEntity = new IncidentEntity();

        incidentEntity.setId( incidentDto.getId() );
        incidentEntity.setName( incidentDto.getName() );

        return incidentEntity;
    }
}
