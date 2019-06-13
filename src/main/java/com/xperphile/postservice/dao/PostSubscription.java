package com.xperphile.postservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "post_subscription")
public class PostSubscription {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "post_id")
    private String post_id;

    @Column(name = "user")
    private String user;

    @Column(name = "creation_time")
    private Timestamp creation_time;

    public PostSubscription() {
    }

    public PostSubscription(String id, String post_id, String user, Timestamp creation_time) {
        this.id = id;
        this.post_id = post_id;
        this.user = user;
        this.creation_time = creation_time;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Timestamp getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

}
