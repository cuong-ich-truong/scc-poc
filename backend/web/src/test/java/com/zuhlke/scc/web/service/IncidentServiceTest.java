package com.zuhlke.scc.web.service;

import com.zuhlke.scc.web.domain.incident.IncidentEntity;
import com.zuhlke.scc.web.domain.incident.IncidentRepository;
import com.zuhlke.scc.web.domain.incident.IncidentService;
import com.zuhlke.scc.web.domain.incident.IncidentDto;
import com.zuhlke.scc.web.domain.incident.IncidentRequest;
import com.zuhlke.scc.web.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IncidentServiceTest {

    @Mock
    private IncidentRepository incidentRepository;

    private IncidentService incidentService; // Assuming this is your service class

    @Test
    public void given_validId_when_findById_then_returnCorrectData() {
        // Prepare mock repository
        IncidentEntity mockEntity = IncidentEntity.builder().name("Test").id("123").build();
        when(incidentRepository.findById("123")).thenReturn(Optional.of(mockEntity));

        // Call the service method and assert response
        IncidentDto incidentDto = incidentService.getIncidentById("123");
        assertEquals("123", incidentDto.getId());
        assertEquals("Test", incidentDto.getName());
    }

    @Test
    public void given_invalidId_when_findById_then_returnCorrectException() {
        when(incidentRepository.findById("123")).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> incidentService.getIncidentById("123"));
    }

    @Test
    public void when_findAll_then_returnAllData() {
        IncidentEntity mockEntity1 = IncidentEntity.builder().name("Test1").id("123").build();
        IncidentEntity mockEntity2 = IncidentEntity.builder().name("Test2").id("456").build();
        when(incidentRepository.findAll()).thenReturn(Arrays.asList(mockEntity1, mockEntity2));

        List<IncidentDto> incidents = incidentService.getAllIncidents();
        assertEquals(2, incidents.size());
    }

    @Test
    public void testCreateIncident() {
        // Given
       IncidentRequest request = IncidentRequest.builder().name("Test").build();
        IncidentEntity entity = IncidentEntity.builder().name("Test").id("123").build();
        when(incidentRepository.save(any())).thenReturn(entity);

        // When
        IncidentDto response = incidentService.createIncident(request);

        // Then
        assertEquals("123", response.getId());
        assertEquals("Test", response.getName());
    }

    @Test
    public void testUpdateIncident() {
        // Given
        IncidentRequest request = IncidentRequest.builder().name("Updated").build();
        IncidentEntity oldEntity = IncidentEntity.builder().name("Test").id("123").build();
        IncidentEntity newEntity = IncidentEntity.builder().name("Updated").id("123").build();
        when(incidentRepository.findById("123")).thenReturn(Optional.of(oldEntity));
        when(incidentRepository.save(any())).thenReturn(newEntity);

        // When
        IncidentDto response = incidentService.updateIncident("123", request);

        // Then
        assertEquals("123", response.getId());
        assertEquals("Updated", response.getName());
    }

    @Test
    public void testUpdateIncident_NotFound() {
        // Given
        IncidentRequest request = IncidentRequest.builder().name("Updated").build();
        when(incidentRepository.findById("123")).thenReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> incidentService.updateIncident("123", request));
    }

    @Test
    public void testDeleteIncident() {
        // Given
        when(incidentRepository.existsById("123")).thenReturn(true);

        // When
        incidentService.deleteIncident("123");

        // Then
        verify(incidentRepository, times(1)).deleteById("123");
    }

    @Test
    public void testDeleteIncident_NotFound() {
        // Given
        when(incidentRepository.existsById("123")).thenReturn(false);

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> incidentService.deleteIncident("123"));
    }

}
