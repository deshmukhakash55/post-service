package com.xperphile.postservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class Recommendation {

    @JsonProperty("post_id")
    private String post_id;

    @JsonProperty("user_id")
    private String user_id;

    @JsonProperty("source_type")
    private String source_type;

    @JsonProperty("source")
    private String source;

    public Recommendation() {
    }

    public Recommendation(String post_id, String user_id, String source_type, String source) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.source_type = source_type;
        this.source = source;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSource_type() {
        return source_type;
    }

    public void setSource_type(String source_type) {
        this.source_type = source_type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
