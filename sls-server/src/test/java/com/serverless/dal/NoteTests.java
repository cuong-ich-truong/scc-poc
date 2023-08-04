package com.serverless.dal;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.serverless.dto.CreateNoteRequestBody;
import org.junit.Assert;
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
public class NoteTests {

    @Mock
    DynamoDBMapper dynamoDBMapper;
    @Mock
    PaginatedScanList paginatedScanList;

    @Test
    public void getAllNotes() throws IOException {

        Note note = new Note();
        note.setId("not_1");
        note.mapper = dynamoDBMapper;

        paginatedScanList.add(note);
        when(dynamoDBMapper.scan(Mockito.eq(Note.class), Mockito.any())).thenReturn(paginatedScanList);
        when(paginatedScanList.size()).thenReturn(1);

        List<Note> result = note.list();
        Assert.assertEquals(result.size(), paginatedScanList.size());
    }

    @Test
    public void getNoteById() {

        Note note = new Note();
        note.setId("not_1");
        note.mapper = dynamoDBMapper;

        when(dynamoDBMapper.load(Mockito.eq(Note.class), Mockito.eq(note.getId()))).thenReturn(note);

        Note result = note.getNotetById(note.getId());
        Assert.assertEquals(result.getId(), note.getId());
    }

    @Test
    public void createNote() throws IOException {

        Note note = new Note();
        note.setId("not_1");
        note.mapper = dynamoDBMapper;

        note.create("inc_1", "desc_1");

        verify(dynamoDBMapper).save(Mockito.eq(note));
    }
}