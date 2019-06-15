package com.xperphile.postservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post_content")
public class PostContent {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "content")
    private String content;

    public PostContent(){}

    public PostContent(String id, String content){
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
