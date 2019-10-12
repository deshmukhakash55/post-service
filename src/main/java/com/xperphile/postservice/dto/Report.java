package com.xperphile.postservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Report {

    @JsonProperty("reporter")
    private String reporter;

    @JsonProperty("report_type")
    private String report_type;

    @JsonProperty("report_text")
    private String report_text;

    @JsonProperty("creation_time")
    private Timestamp creation_time;

    public Report() {
    }

    public Report(String reporter, String report_type, String report_text, Timestamp creation_time) {
        this.reporter = reporter;
        this.report_type = report_type;
        this.report_text = report_text;
        this.creation_time = creation_time;
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
