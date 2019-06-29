package com.xperphile.funnelservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xperphile.postservice.dto.Report;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class PostReports implements Serializable {

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
