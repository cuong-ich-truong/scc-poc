package com.serverless.dal;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PremiseTests {

    @Mock
    DynamoDBMapper dynamoDBMapper;
    @Mock
    DynamoDBMapper cameraDynamoDBMapper;
    @Mock
    PaginatedQueryList paginatedQueryList;
    @Mock
    PaginatedScanList<Premise> paginatedScanList;
    @Mock
    PaginatedScanList<Camera> cameraPaginatedScanList;

    @Test
    public void getAllPremises() throws IOException {

        Premise premise = new Premise();
        premise.setId("Pre_1");
        premise.mapper = dynamoDBMapper;

        paginatedScanList.add(premise);
        when(dynamoDBMapper.scan(Mockito.eq(Premise.class), Mockito.any())).thenReturn(paginatedScanList);
        when(paginatedScanList.size()).thenReturn(1);

        List<Premise> result = premise.list();
        Assert.assertEquals(result.size(), paginatedScanList.size());
    }

    @Ignore("TODO: Find a way to mock Camera and Incident when get premises list")
    @Test
    public void getAllPremisesWithRelations() throws IOException {

        // TODO: mock static DynamoDBAdapter.getInstance()
        Camera camera = new Camera();
        camera.mapper = cameraDynamoDBMapper;
        cameraPaginatedScanList.add(camera);

        Premise premise = new Premise();
        premise.setId("Pre_1");
        premise.getCameras().add(camera);
        premise.mapper = dynamoDBMapper;
        paginatedScanList.add(premise);

        when(cameraDynamoDBMapper.scan(Mockito.eq(Camera.class), Mockito.any())).thenReturn(cameraPaginatedScanList);
        when(cameraPaginatedScanList.size()).thenReturn(1);

        when(dynamoDBMapper.scan(Mockito.eq(Premise.class), Mockito.any())).thenReturn(paginatedScanList);
        when(paginatedScanList.size()).thenReturn(1);

        List<Premise> result = premise.listWithRelations();
        Assert.assertEquals(result.size(), paginatedScanList.size());
        Assert.assertEquals(result.get(0).toString(), paginatedScanList.get(0).toString());
        Assert.assertEquals(result.get(0).getCameras().size(), paginatedScanList.get(0).getCameras().size());
        Assert.assertEquals(result.get(0).getCameras().get(0).toString(), paginatedScanList.get(0).getCameras().get(0).toString());
    }

    @Test
    public void getAllPremises_Empty() throws IOException {

        Premise premise = new Premise();
        premise.mapper = dynamoDBMapper;

        when(dynamoDBMapper.scan(Mockito.eq(Premise.class), Mockito.any())).thenReturn(paginatedScanList);
        when(paginatedScanList.size()).thenReturn(0);

        List<Premise> result = premise.list();
        Assert.assertEquals(result.size(), paginatedScanList.size());
    }

    @Ignore("TODO: Find a way to mock Camera and Incident when get premises list")
    @Test
    public void getAllPremisesWithRelations_NoCameras() throws IOException {

        Premise premise = new Premise();
        premise.setId("Pre_1");
        premise.mapper = dynamoDBMapper;
        paginatedScanList.add(premise);

        when(dynamoDBMapper.scan(Mockito.eq(Premise.class), Mockito.any())).thenReturn(paginatedScanList);
        when(paginatedScanList.size()).thenReturn(1);

        List<Premise> result = premise.listWithRelations();
        Assert.assertEquals(result.size(), paginatedScanList.size());
        Assert.assertEquals(result.get(0).toString(), paginatedScanList.get(0).toString());
        Assert.assertEquals(result.get(0).getCameras().size(), 0);
    }

    @Ignore("TODO: Find a way to mock Camera and Incident when get premises list")
    @Test
    public void getAllPremisesWithRelations_CamerasNoIncidents() throws IOException {

        Camera camera = new Camera();
        camera.setId("Cam_1");
        camera.mapper = cameraDynamoDBMapper;
        cameraPaginatedScanList.add(camera);

        Premise premise = new Premise();
        premise.setId("Pre_1");
        premise.getCameras().add(camera);
        premise.mapper = dynamoDBMapper;
        paginatedScanList.add(premise);

        when(cameraDynamoDBMapper.scan(Mockito.eq(Camera.class), Mockito.any())).thenReturn(cameraPaginatedScanList);
        when(cameraPaginatedScanList.size()).thenReturn(1);

        when(dynamoDBMapper.scan(Mockito.eq(Premise.class), Mockito.any())).thenReturn(paginatedScanList);
        when(paginatedScanList.size()).thenReturn(1);

        List<Premise> result = premise.listWithRelations();
        Assert.assertEquals(result.size(), paginatedScanList.size());
        Assert.assertEquals(result.get(0).toString(), paginatedScanList.get(0).toString());
        Assert.assertEquals(result.get(0).getCameras().size(), cameraPaginatedScanList.size());
        Assert.assertEquals(result.get(0).getCameras().get(0).toString(), cameraPaginatedScanList.get(0).toString());
        Assert.assertEquals(result.get(0).getCameras().get(0).getIncidents().size(), 0);
    }
}