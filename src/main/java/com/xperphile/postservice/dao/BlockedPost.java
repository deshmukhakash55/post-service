package com.xperphile.postservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "blocked_post")
public class BlockedPost {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "post_id")
    private String post_id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "creation_time")
    private Timestamp timestamp;

    public BlockedPost() {
    }

    public BlockedPost(String id, String post_id, String user_id, Timestamp timestamp) {
        this.id = id;
        this.post_id = post_id;
        this.user_id = user_id;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
