package com.serverless.dal;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GuardTests {

    @Mock
    DynamoDBMapper dynamoDBMapper;
    @Mock
    PaginatedQueryList paginatedQueryList;
    @Mock
    PaginatedScanList paginatedScanList;

    @Test
    public void getAllGuards() throws IOException {

        Guard guard = new Guard();
        guard.setId("gua_1");
        guard.mapper = dynamoDBMapper;

        paginatedScanList.add(guard);
        when(dynamoDBMapper.scan(Mockito.eq(Guard.class), Mockito.any())).thenReturn(paginatedScanList);
        when(paginatedScanList.size()).thenReturn(1);

        List<Guard> result = guard.list();
        Assert.assertEquals(result.size(), paginatedScanList.size());
    }

    @Test
    public void getGuardById() throws IOException {

        Guard guard = new Guard();
        guard.setId("gua_1");
        guard.mapper = dynamoDBMapper;

        paginatedQueryList.add(guard);
        when(dynamoDBMapper.query(Mockito.eq(Guard.class), Mockito.any())).thenReturn(paginatedQueryList);
        when(paginatedQueryList.size()).thenReturn(1);
        when(paginatedQueryList.get(Mockito.anyInt())).thenReturn(guard);

        Guard result = guard.get(guard.getId());
        Assert.assertEquals(result.getId(), guard.getId());
    }

    @Test
    public void updateGuardToken() {
        Guard guard = new Guard();
        guard.setId("gua_1");
        guard.mapper = dynamoDBMapper;
        String token = "test_token";

        when(dynamoDBMapper.load(Mockito.eq(Guard.class), Mockito.eq(guard.getId()))).thenReturn(guard);

        guard.updateToken(guard.getId(), token);
        guard.setToken(token);
        verify(dynamoDBMapper).save(Mockito.eq(guard));
    }

    @Test
    public void saveGuard() {
        Guard guard = new Guard();
        guard.setId("gua_1");
        guard.mapper = dynamoDBMapper;

        guard.save(guard);
        verify(dynamoDBMapper).save(Mockito.eq(guard));
    }
}