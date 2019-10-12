package com.xperphile.postservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostReports {

    @JsonProperty("post")
    private DisplayPost displayPost;

    @JsonProperty("reports")
    private List<Report> reports;

    public PostReports() {
    }

    public PostReports(DisplayPost displayPost, List<Report> reports) {
        this.displayPost = displayPost;
        this.reports = reports;
    }

    public DisplayPost getDisplayPost() {
        return displayPost;
    }

    public void setDisplayPost(DisplayPost displayPost) {
        this.displayPost = displayPost;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
