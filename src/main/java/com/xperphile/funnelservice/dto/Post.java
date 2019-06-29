package com.xperphile.funnelservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Post implements Serializable {

    @JsonProperty("owner_id")
    private String owner_id;

    @JsonProperty("content")
    private String content;

    public Post() {
    }

    public Post(String owner_id, String content) {
        this.owner_id = owner_id;
        this.content = content;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
