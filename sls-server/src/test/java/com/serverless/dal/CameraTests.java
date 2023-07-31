package com.serverless.dal;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.junit.Assert;
import org.mockito.Mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CameraTests {

    @Mock
    DynamoDBMapper dynamoDBMapper;
    @Mock
    PaginatedQueryList paginatedQueryList;
    @Mock
    PaginatedScanList paginatedScanList;

    @Test
    public void getAllCameras() throws IOException {

        Camera camera = new Camera();
        camera.setId("cam_1");
        camera.mapper = dynamoDBMapper;
        paginatedScanList.add(camera);

        when(dynamoDBMapper.scan(Mockito.eq(Camera.class), Mockito.any())).thenReturn(paginatedScanList);
        when(paginatedScanList.size()).thenReturn(1);

        List<Camera> result = camera.list();
        Assert.assertEquals(result.size(), paginatedScanList.size());
    }

    @Test
    public void getCameraById() throws IOException {

        Camera camera = new Camera();
        camera.setId("cam_1");
        camera.mapper = dynamoDBMapper;
        paginatedQueryList.add(camera);

        when(dynamoDBMapper.query(Mockito.eq(Camera.class), Mockito.any())).thenReturn(paginatedQueryList);
        when(paginatedQueryList.size()).thenReturn(1);
        when(paginatedQueryList.get(Mockito.anyInt())).thenReturn(camera);

        Camera cam = camera.get(camera.getId());
        Assert.assertEquals(cam.getId(), camera.getId());
    }
}