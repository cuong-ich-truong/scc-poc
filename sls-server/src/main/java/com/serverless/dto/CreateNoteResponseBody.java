package com.serverless.dto;

import java.net.URL;

public class CreateNoteResponseBody {

    private String id;
    private String description;

    private URL videoUrl;

    private URL pictureUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(URL videoUrl) {
        this.videoUrl = videoUrl;
    }

    public URL getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(URL pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
