package com.xperphile.postservice.controller;

import com.xperphile.postservice.constant.ClientConstants;
import com.xperphile.postservice.dto.DisplayPost;
import com.xperphile.postservice.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ClientConstants.BLOCK_CONTROLLER_MAPPING)
public class BlockController {

    @Autowired
    private BlockService blockService;

    @PostMapping(ClientConstants.CLIENT_ADD_BLOCKED_POST)
    public ResponseEntity blockPost(@RequestBody Map<String, String> map){
        try{
            blockService.blockPost(map.get("post_id"), map.get("user_id"));
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_REMOVE_BLOCKED_POST)
    public ResponseEntity unblockPost(@RequestBody Map<String, String> map){
        try{
            blockService.unblockPost(map.get("id"));
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_GET_BLOCKED_POSTS)
    public ResponseEntity getBlockedPosts(@PathVariable("user_id") String user_id){
        try{
            List<DisplayPost> blockedPosts = blockService.getBlockedPosts(user_id);
            return new ResponseEntity(blockedPosts, HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
