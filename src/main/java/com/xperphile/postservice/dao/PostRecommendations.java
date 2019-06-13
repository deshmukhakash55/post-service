package com.xperphile.postservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "post_recommendations")
public class PostRecommendations {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "post_id")
    private String post_id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "source_type")
    private String source_type;

    @Column(name = "source")
    private String source;

    @Column(name = "creation_time")
    private Timestamp creation_time;

    @Column(name = "expiry_time")
    private Timestamp expiry_time;

    public PostRecommendations() {
    }

    public PostRecommendations(String id, String post_id, String user_id, String source_type, String source, Timestamp creation_time, Timestamp expiry_time) {
        this.id = id;
        this.post_id = post_id;
        this.user_id = user_id;
        this.source_type = source_type;
        this.source = source;
        this.creation_time = creation_time;
        this.expiry_time = expiry_time;
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

    public Timestamp getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    public Timestamp getExpiry_time() {
        return expiry_time;
    }

    public void setExpiry_time(Timestamp expiry_time) {
        this.expiry_time = expiry_time;
    }
}
