package com.xperphile.postservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class RawComment {

    @JsonProperty("post_id")
    private String post_id;

    @JsonProperty("user")
    private String user;

    @JsonProperty("comment_content")
    private String comment_content;

    @JsonProperty("comment_type")
    private String comment_type;

    @JsonProperty("referred_comment")
    private String referred_comment;

    public RawComment() {
    }

    public RawComment(String post_id, String user, String comment_content, String comment_type, String referred_comment) {
        this.post_id = post_id;
        this.user = user;
        this.comment_content = comment_content;
        this.comment_type = comment_type;
        this.referred_comment = referred_comment;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_type() {
        return comment_type;
    }

    public void setComment_type(String comment_type) {
        this.comment_type = comment_type;
    }

    public String getReferred_comment() {
        return referred_comment;
    }

    public void setReferred_comment(String referred_comment) {
        this.referred_comment = referred_comment;
    }

}
