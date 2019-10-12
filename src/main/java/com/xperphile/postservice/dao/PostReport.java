package com.xperphile.postservice.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "post_report")
public class PostReport {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "post_id")
    private String post_id;

    @Column(name = "reporter")
    private String reporter;

    @Column(name = "report_type")
    private String report_type;

    @Column(name = "report_text")
    private String report_text;

    @Column(name = "creation_time")
    private Timestamp creation_time;

    public PostReport() {
    }

    public PostReport(String id, String post_id, String reporter, String report_type, String report_text, Timestamp creation_time) {
        this.id = id;
        this.post_id = post_id;
        this.reporter = reporter;
        this.report_type = report_type;
        this.report_text = report_text;
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

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getReport_text() {
        return report_text;
    }

    public void setReport_text(String report_text) {
        this.report_text = report_text;
    }

    public Timestamp getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }
}
