package com.zuhlke.scc.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zuhlke.scc.web.domain.incident.IncidentController;
import com.zuhlke.scc.web.domain.incident.IncidentDto;
import com.zuhlke.scc.web.domain.incident.IncidentRequest;
import com.zuhlke.scc.web.domain.incident.IncidentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IncidentController.class)
public class IncidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IncidentService incidentService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void testGetIncidentById() throws Exception {
        IncidentDto incidentDto = IncidentDto.builder().id("123").name("Test").build();

        when(incidentService.getIncidentById("123")).thenReturn(incidentDto);

        mockMvc.perform(get("/incidents/123"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value("123"))
            .andExpect(jsonPath("$.name").value("Test"));

        verify(incidentService, times(1)).getIncidentById("123");
    }

    @Test
    public void testGetAllIncidents() throws Exception {
        IncidentDto incidentDto1 = IncidentDto.builder().id("123").name("Test").build();
        IncidentDto incidentDto2 = IncidentDto.builder().id("456").name("Test2").build();

        List<IncidentDto> incidentDtos = Arrays.asList(incidentDto1, incidentDto2);

        when(incidentService.getAllIncidents()).thenReturn(incidentDtos);

        mockMvc.perform(get("/incidents"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value("123"))
            .andExpect(jsonPath("$[0].name").value("Test"))
            .andExpect(jsonPath("$[1].id").value("456"))
            .andExpect(jsonPath("$[1].name").value("Test2"));

        verify(incidentService, times(1)).getAllIncidents();
    }

    @Test
    public void testCreateIncident() throws Exception {
        IncidentRequest incidentRequest = IncidentRequest.builder().name("Test").build();
        IncidentDto incidentDto = IncidentDto.builder().id("123").name("Test").build();

        when(incidentService.createIncident(any(IncidentRequest.class))).thenReturn(incidentDto);

        mockMvc.perform(post("/incidents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incidentRequest)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value("123"))
            .andExpect(jsonPath("$.name").value("Test"));

        verify(incidentService, times(1)).createIncident(any(IncidentRequest.class));
    }

    @Test
    public void testUpdateIncident() throws Exception {
        IncidentRequest incidentRequest = IncidentRequest.builder().name("Test").build();
        IncidentDto incidentDto = IncidentDto.builder().id("123").name("Test").build();

        when(incidentService.updateIncident(anyString(), any(IncidentRequest.class))).thenReturn(incidentDto);

        mockMvc.perform(put("/incidents/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incidentRequest)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value("123"))
            .andExpect(jsonPath("$.name").value("Test"));

        verify(incidentService, times(1)).updateIncident(anyString(), any(IncidentRequest.class));
    }

    @Test
    public void testDeleteIncident() throws Exception {
        doNothing().when(incidentService).deleteIncident("123");

        mockMvc.perform(delete("/incidents/123"))
            .andExpect(status().isNoContent());

        verify(incidentService, times(1)).deleteIncident("123");
    }
}
