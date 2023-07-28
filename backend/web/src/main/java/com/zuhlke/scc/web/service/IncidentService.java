package com.zuhlke.scc.web.service;

import com.zuhlke.scc.web.db.entity.IncidentEntity;
import com.zuhlke.scc.web.db.repository.IncidentRepository;
import com.zuhlke.scc.web.dto.IncidentDto;
import com.zuhlke.scc.web.dto.IncidentRequest;
import com.zuhlke.scc.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentDto getIncidentById(String incidentId) {
        Optional<IncidentEntity> incidentEntityOptional = incidentRepository.findById(incidentId);

        if (incidentEntityOptional.isEmpty())
            throw new EntityNotFoundException("Incident " + incidentId + " not found.");

        return toDtoFromEntityIncident(incidentEntityOptional.get());
    }


    public List<IncidentDto> getAllIncidents() {
        List<IncidentEntity> incidentEntityList = incidentRepository.findAll();

        if (incidentEntityList.size() == 0)
            return Collections.EMPTY_LIST;

        return incidentEntityList.stream().map(this::toDtoFromEntityIncident).toList();
    }


    @Transactional
    public IncidentDto  createIncident(IncidentRequest incidentRequest) {
        try {
            IncidentEntity incidentEntity = toEntityFromRequestIncident(incidentRequest);
            return toDtoFromEntityIncident(incidentRepository.save(incidentEntity));

        } catch (DataAccessException dae) {
            throw new RuntimeException("Could not save incident " + incidentRequest.getName(), dae);
        }
    }

    @Transactional
    public IncidentDto updateIncident(String id, IncidentRequest incidentRequest) {
        Optional<IncidentEntity> existingIncident = incidentRepository.findById(id);

        if (!existingIncident.isPresent()) {
            throw new EntityNotFoundException("Incident not found with id: " + id);
        }

        IncidentEntity updatedIncident = toEntityFromRequestIncident(incidentRequest);
        updatedIncident.setId(id); // Ensure the ID is preserved.

        return toDtoFromEntityIncident(incidentRepository.save(updatedIncident));
    }

    @Transactional
    public void deleteIncident(String id) {
        if (!incidentRepository.existsById(id)) {
            throw new EntityNotFoundException("Incident not found with id: " + id);
        }

        incidentRepository.deleteById(id);
    }

    private IncidentEntity toEntityFromRequestIncident(IncidentRequest incidentRequest) {
        return IncidentEntity.builder()
            .name(incidentRequest.getName())
            .build();
    }

    private IncidentDto toDtoFromEntityIncident(IncidentEntity incidentEntity) {
        return IncidentDto.builder()
            .name(incidentEntity.getName())
            .id(incidentEntity.getId())
            .build();
    }
}
