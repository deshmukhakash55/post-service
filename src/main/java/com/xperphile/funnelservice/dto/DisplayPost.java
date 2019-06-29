package com.xperphile.funnelservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

@Component
public class DisplayPost implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("owner_id")
    private String owner_id;

    @JsonProperty("content")
    private byte[] content;

    @JsonProperty("comments")
    private BigInteger comments;

    @JsonProperty("emojis")
    private BigInteger emojis;

    @JsonProperty("tags")
    private String tags;

    @JsonProperty("tagged_users")
    private String tagged_users;

    @JsonProperty("creation_time")
    private Timestamp creation_time;

    @JsonProperty("latest_modified_time")
    private Timestamp latest_modified_time;

    public DisplayPost() {
    }

    public DisplayPost(String id, String owner_id, byte[] content, BigInteger comments, BigInteger emojis, String tags, String tagged_users, Timestamp creation_time, Timestamp latest_modified_time) {
        this.id = id;
        this.owner_id = owner_id;
        this.content = content;
        this.comments = comments;
        this.emojis = emojis;
        this.tags = tags;
        this.tagged_users = tagged_users;
        this.creation_time = creation_time;
        this.latest_modified_time = latest_modified_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
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

    public Timestamp getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    public Timestamp getlatest_modified_time() {
        return latest_modified_time;
    }

    public void setlatest_modified_time(Timestamp latest_modified_time) {
        this.latest_modified_time = latest_modified_time;
    }
}
