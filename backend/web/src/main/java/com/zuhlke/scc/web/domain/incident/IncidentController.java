package com.zuhlke.scc.web.domain.incident;

import com.zuhlke.scc.web.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @GetMapping("/{id}")
    public ResponseEntity<IncidentDto> getIncidentById(@PathVariable("id") String incidentId) {
        try {
            IncidentDto incidentDto = incidentService.getIncidentById(incidentId);
            return new ResponseEntity<>(incidentDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Incident not found", e);
        }
    }

    @GetMapping
    public ResponseEntity<List<IncidentDto>> getAllIncidents() {
        List<IncidentDto> incidents = incidentService.getAllIncidents();
        return new ResponseEntity<>(incidents, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IncidentDto> createIncident(@RequestBody IncidentRequest incidentRequest) {
        IncidentDto createdIncidentDto = incidentService.createIncident(incidentRequest);
        return new ResponseEntity<>(createdIncidentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncidentById(@PathVariable("id") String id) {
        try {
            incidentService.deleteIncident(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Incident not found", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidentDto> updateIncident(@PathVariable("id") String id, @RequestBody IncidentRequest incidentRequest) {
        try {
            IncidentDto updatedIncidentDto = incidentService.updateIncident(id, incidentRequest);
            return new ResponseEntity<>(updatedIncidentDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Incident not found", e);
        }
    }
}
