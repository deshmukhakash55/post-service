package com.xperphile.postservice.controller;

import com.xperphile.postservice.constant.ClientConstants;
import com.xperphile.postservice.dto.PostReports;
import com.xperphile.postservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(ClientConstants.REPORT_CONTROLLER_MAPPING)
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping(ClientConstants.CLIENT_ADD_POST_REPORT)
    public ResponseEntity addPostReport(@RequestBody Map<String, String> map){
        try{
            reportService.addPostReport(map.get("post_id"), map.get("reporter"), map.get("report_type"), map.get("report_text"));
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception)
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_REMOVE_POST_REPORT)
    public ResponseEntity removePostReport(@RequestBody Map<String, String> map){
        try{
            reportService.removePostReport(map.get("id"));
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception)
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_GET_POST_REPORTS)
    public ResponseEntity getPostReports(@PathVariable("post_id") String post_id){
        try{
            PostReports postReports = reportService.getPostReports(post_id);
            return new ResponseEntity(postReports, HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception)
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
