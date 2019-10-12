package com.xperphile.postservice.controller;

import com.xperphile.postservice.constant.ClientConstants;
import com.xperphile.postservice.dto.DisplayPost;
import com.xperphile.postservice.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ClientConstants.SEARCH_CONTROLLER_MAPPING)
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping(ClientConstants.CLIENT_SEARCH_BY_TAG)
    public ResponseEntity searchPostsByTag(@RequestBody Map<String, String> map){
        try{
            List<DisplayPost> posts = searchService.searchPostsByTag(map.get("tag"));
            return new ResponseEntity(posts, HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_SEARCH_BY_TAGGED_USER)
    public ResponseEntity searchPostsByTaggedUser(@RequestBody Map<String, String> map){
        try{
            List<DisplayPost> posts = searchService.searchPostsByTaggedUser(map.get("tagged_user"));
            return new ResponseEntity(posts, HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_SEARCH_BY_TEXT)
    public ResponseEntity searchPostsBySearchedText(@RequestBody Map<String, String> map){
        try{
            List<DisplayPost> posts = searchService.searchPostsBySearchedText(map.get("searched_text"));
            return new ResponseEntity(posts, HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
