package com.xperphile.postservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "post_meta")
public class PostMeta {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "owner")
    private String owner;

    @Column(name = "creation_time")
    private Timestamp creation_time;

    @Column(name = "latest_modified_time")
    private Timestamp latest_modified_time;

    @Column(name = "comments")
    private BigInteger comments;

    @Column(name = "emojis")
    private BigInteger emojis;

    @Column(name = "tags")
    private String tags;

    @Column(name = "tagged_users")
    private String tagged_users;

    @Column(name = "name")
    private String name;

    public PostMeta() {
    }

    public PostMeta(String id, String owner, Timestamp creation_time, Timestamp latest_modified_time, BigInteger comments, BigInteger emojis, String tags, String tagged_users, String name) {
        this.id = id;
        this.owner = owner;
        this.creation_time = creation_time;
        this.latest_modified_time = latest_modified_time;
        this.comments = comments;
        this.emojis = emojis;
        this.tags = tags;
        this.tagged_users = tagged_users;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Timestamp getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    public Timestamp getLatest_modified_time() {
        return latest_modified_time;
    }

    public void setLatest_modified_time(Timestamp latest_modified_time) {
        this.latest_modified_time = latest_modified_time;
    }

    public BigInteger getComments() {
        return comments;
    }

    public void setComments(BigInteger comments) {
        this.comments = comments;
    }

    public BigInteger getEmojis() {
        return emojis;
    }

    public void setEmojis(BigInteger emojis) {
        this.emojis = emojis;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTagged_users() {
        return tagged_users;
    }

    public void setTagged_users(String tagged_users) {
        this.tagged_users = tagged_users;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
