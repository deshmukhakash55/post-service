package com.xperphile.postservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user")
    private String user;

    @Column(name = "post_id")
    private String post_id;

    @Column(name = "comment_content")
    private byte[] comment_content;

    @Column(name = "comment_type")
    private String comment_type;

    @Column(name = "referred_comment")
    private String referred_comment;

    public Comment() {
    }

    public Comment(String id, String user, String post_id, byte[] comment_content, String comment_type, String referred_comment) {
        this.id = id;
        this.user = user;
        this.post_id = post_id;
        this.comment_content = comment_content;
        this.comment_type = comment_type;
        this.referred_comment = referred_comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public byte[] getComment_content() {
        return comment_content;
    }

    public void setComment_content(byte[] comment_content) {
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
