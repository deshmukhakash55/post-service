package com.xperphile.postservice.service;

import com.xperphile.postservice.dao.PostContent;
import com.xperphile.postservice.dao.PostMeta;
import com.xperphile.postservice.dao.PostReport;
import com.xperphile.postservice.dto.DisplayPost;
import com.xperphile.postservice.dto.PostReports;
import com.xperphile.postservice.dto.Report;
import com.xperphile.postservice.repository.PostContentRepository;
import com.xperphile.postservice.repository.PostMetaRepository;
import com.xperphile.postservice.repository.PostReportRepository;
import com.xperphile.postservice.utility.Base64Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class ReportService {

    @Autowired
    private PostReportRepository postReportRepository;

    @Autowired
    private PostMetaRepository postMetaRepository;

    @Autowired
    private PostContentRepository postContentRepository;

    @Autowired
    private ClientService clientService;

    public void addPostReport(String post_id, String reporter, String report_type, String report_text) throws IllegalArgumentException{
        if(post_id == null || post_id.isEmpty())
            throw new IllegalArgumentException("Illegal post_id");
        if(reporter == null || reporter.isEmpty())
            throw new IllegalArgumentException("Illegal reporter");
        if(report_type == null || report_type.isEmpty())
            throw new IllegalArgumentException("Illegal report_type");
        String id = UUID.randomUUID().toString();
        Timestamp creation_time = new Timestamp(new Date().getTime());
        PostReport postReport = new PostReport(id, post_id, reporter, report_type, report_text, creation_time);
        postReportRepository.save(postReport);
    }

    public void removePostReport(String id) throws IllegalArgumentException{
        if(id == null || id.isEmpty())
            throw new IllegalArgumentException("Illegal id");
        postReportRepository.deleteById(id);
    }

    public PostReports getPostReports(String post_id) throws IllegalArgumentException{
        if(post_id == null || post_id.isEmpty())
            throw new IllegalArgumentException("Illegal post_id");
        List<PostReport> postReportList = postReportRepository.findByPost_Id(post_id);
        DisplayPost displayPost = getDisplayPostById(post_id);
        List<Report> reports = new ArrayList<>();
        postReportList.stream().forEach(postReport -> {
            Report report = new Report(postReport.getReporter(), postReport.getReport_type(), postReport.getReport_text(), postReport.getCreation_time());
            reports.add(report);
        });
        PostReports postReports = new PostReports(displayPost, reports);
        return postReports;
    }

    public DisplayPost getDisplayPostById(String id){
        PostMeta postMeta = postMetaRepository.findById(id).get();
        PostContent postContent = postContentRepository.findById(id).get();
        DisplayPost displayPost = new DisplayPost(id, postMeta.getOwner(), Base64Utility.decode(postContent.getContent()),postMeta.getComments(), postMeta.getEmojis(), postMeta.getTags(), postMeta.getTagged_users(), postMeta.getCreation_time(), postMeta.getLatest_modified_time());
        return displayPost;
    }

}
