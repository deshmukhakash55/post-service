package com.xperphile.postservice.controller;

import com.xperphile.postservice.constant.ClientConstants;
import com.xperphile.postservice.dto.DisplayPost;
import com.xperphile.postservice.dto.Post;
import com.xperphile.postservice.dto.RawComment;
import com.xperphile.postservice.dto.Recommendation;
import com.xperphile.postservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = ClientConstants.CLIENT_CONTROLLER_MAPPING)
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping(ClientConstants.CLIENT_ADD_POSTS)
    public ResponseEntity addPost(@RequestBody Post post){
        try{
            clientService.addPost(post);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch(IllegalArgumentException exception){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_ADD_TAG_TO_POST)
    public ResponseEntity addTagToPost(@RequestBody Map<String, String > map){
        try{
            clientService.addTagToPost(map.get("post_id"), map.get("tag"));
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_REMOVE_TAG_FROM_POST)
    public ResponseEntity removeTagFromPost(@RequestBody Map<String, String> map){
        try{
            clientService.removeTagFromPost(map.get("post_id"), map.get("tag"));
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_REMOVE_TAGGED_USER_FROM_POST)
    public ResponseEntity removeTaggedUserFromPost(@RequestBody Map<String, String> map){
        try{
            clientService.removeTaggedUserFromPost(map.get("post_id"), map.get("tagged_user"));
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_ADD_TAGGED_USER_TO_POST)
    public ResponseEntity addTaggedUserToPost(@RequestBody Map<String, String> map){
        try{
            clientService.addTaggedUserToPost(map.get("post_id"), map.get("tagged_user"));
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_UPDATE_POST)
    public ResponseEntity updatePost(@RequestBody Post post, @PathVariable("post_id") String post_id){
        try{
            clientService.updatePost(post, post_id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_ADD_COMMENT_OR_EMOJI)
    public ResponseEntity addCommentOrEmoji(@RequestBody RawComment rawComment){
        try{
            clientService.addCommentOrEmoji(rawComment);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_ADD_RECOMMENDATION)
    public ResponseEntity addRecommendation(@RequestBody Recommendation recommendation){
        try{
            clientService.addRecommendation(recommendation);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_REMOVE_RECOMMENDATION)
    public ResponseEntity removeRecommendation(@RequestBody Map<String, String> map){
        try{
            clientService.removeRecommendation(map.get("post_id"), map.get("user_id"));
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_GET_RECOMMENDATION)
    public ResponseEntity getRecommendedPosts(@RequestBody Map<String, String> map){
        try{
            List<DisplayPost> posts = clientService.getRecommendedPosts(map.get("user_id"));
            return new ResponseEntity(posts, HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_GET_POSTS)
    public ResponseEntity getUserPosts(@RequestBody Map<String, String> map){
        try{
            List<DisplayPost> posts = clientService.getUserPosts(map.get("owner"));
            return new ResponseEntity(posts, HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
