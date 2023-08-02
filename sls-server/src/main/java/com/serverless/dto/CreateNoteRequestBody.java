package com.serverless.dto;

import org.springframework.web.multipart.MultipartFile;

public class CreateNoteRequestBody {

    private String description;
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}