package com.serverless.dal;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.serverless.dto.UpdateIncidentRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IncidentTests {

    @Mock
    DynamoDBMapper dynamoDBMapper;
    @Mock
    AmazonDynamoDB amazonDynamoDB;
    @Mock
    PaginatedScanList<Incident> paginatedScanList;

    @Test
    public void listTest() throws IOException {
        Incident incident = new Incident();
        incident.setId("Inc_1");
        incident.setName("Test Incident");
        paginatedScanList.add(incident);
        when(dynamoDBMapper.scan(Incident.class, new DynamoDBScanExpression())).thenReturn(paginatedScanList);

        List<Incident> result = incident.list();
        Assert.assertEquals(result.size(), paginatedScanList.size());
        verify(dynamoDBMapper).scan(Incident.class, new DynamoDBScanExpression());
    }

    @Test
    public void getIncidentByIdTest() throws IOException {
        String testId = "Inc_1";
        Incident incident = new Incident();
        incident.setId(testId);
        incident.setName("Test Incident");
        when(dynamoDBMapper.load(Incident.class, testId)).thenReturn(incident);

        Incident result = incident.get(testId);
        Assert.assertEquals(result.getId(), testId);
        verify(dynamoDBMapper).load(Incident.class, testId);
    }

    @Test
    public void updateIncidentTest() {
        String testId = "Inc_1";
        Incident incident = new Incident();
        incident.setId(testId);
        incident.setName("Test Incident");

        String newGuardId = "guard_1";
        UpdateIncidentRequest updateIncidentRequest = new UpdateIncidentRequest();
        updateIncidentRequest.guardId = newGuardId;

        when(dynamoDBMapper.load(Incident.class, testId)).thenReturn(incident);
        Incident result = incident.update(testId, updateIncidentRequest);
        Assert.assertEquals(result.getGuardId(), newGuardId);
        verify(dynamoDBMapper).load(Incident.class, testId);
    }

    @Test
    public void getIncidentsByGuardIdTest() throws IOException {
        String testGuardId = "guard_1";
        Incident incident1 = new Incident();
        incident1.setId("Inc_1");
        incident1.setName("Test Incident 1");
        incident1.setGuardId(testGuardId);

        Incident incident2 = new Incident();
        incident2.setId("Inc_2");
        incident2.setName("Test Incident 2");
        incident2.setGuardId("guard_2");

        paginatedScanList.add(incident1);
        paginatedScanList.add(incident2);
        when(dynamoDBMapper.scan(Incident.class, new DynamoDBScanExpression())).thenReturn(paginatedScanList);

        Incident incident = new Incident();
        List<Incident> result = incident.getIncidentsByGuardId(testGuardId);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getGuardId(), testGuardId);
        verify(dynamoDBMapper).scan(Incident.class, new DynamoDBScanExpression());
    }

    @Test
    public void testUpdateIncident() {
        String id = "1";
        String guardId = "2";
        UpdateIncidentRequest request = new UpdateIncidentRequest();
        request.setGuardId(guardId);

        Incident incident = new Incident();
        incident.setGuardId(null);
        incident.setMapper(dynamoDBMapper);

        when(dynamoDBMapper.load(Mockito.eq(Incident.class), Mockito.eq(id))).thenReturn(incident);

        incident.update(id, request);

        Assert.assertEquals(incident.getGuardId(), guardId);
        verify(dynamoDBMapper).save(incident);
    }

    @Test
    public void testGetIncidentsByGuardId() throws IOException {
        String guardId = "1";

        Incident incident1 = new Incident();
        incident1.setGuardId(guardId);

        Incident incident2 = new Incident();
        incident2.setGuardId(guardId);

        paginatedScanList.addAll(Arrays.asList(incident1, incident2));

        when(dynamoDBMapper.scan(Mockito.eq(Incident.class), Mockito.any(DynamoDBScanExpression.class))).thenReturn(paginatedScanList);

        Incident incident = new Incident();
        incident.setMapper(dynamoDBMapper);

        List<Incident> results = incident.getIncidentsByGuardId(guardId);

        Assert.assertEquals(results.size(), 2);
        for (Incident result : results) {
            Assert.assertEquals(result.getGuardId(), guardId);
        }
    }

    @Test
    public void testGetIncidentsExcludedIds() throws IOException {
        String[] excludedIds = {"1", "2"};

        Incident incident1 = new Incident();
        incident1.setId("3");

        Incident incident2 = new Incident();
        incident2.setId("4");

        paginatedScanList.addAll(Arrays.asList(incident1, incident2));

        HashMap<String, AttributeValue> attributeValues = new HashMap<>();
        attributeValues.put(":id1", new AttributeValue().withS("1"));
        attributeValues.put(":id2", new AttributeValue().withS("2"));

        when(dynamoDBMapper.scan(Mockito.eq(Incident.class), Mockito.any(DynamoDBScanExpression.class), Mockito.any(DynamoDBMapperConfig.class))).thenReturn(paginatedScanList);

        Incident incident = new Incident();
        incident.setMapper(dynamoDBMapper);

        List<Incident> results = incident.getIncidentsExcludedIds(excludedIds);

        Assert.assertEquals(results.size(), 2);
        for (Incident result : results) {
            Assert.assertFalse(Arrays.asList(excludedIds).contains(result.getId()));
        }
    }
}
