package com.xperphile.postservice.controller;

import com.xperphile.postservice.constant.ClientConstants;
import com.xperphile.postservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(ClientConstants.SUBSCRIPTION_CONTROLLER_MAPPING)
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping(ClientConstants.CLIENT_ADD_POST_SUBSCRIPTION)
    public ResponseEntity addSubscription(@RequestBody Map<String, String> map){
        try{
            subscriptionService.addSubscription(map.get("post_id"), map.get("user_id"));
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ClientConstants.CLIENT_REMOVE_POST_SUBSCRIPTION)
    public ResponseEntity removeSubscription(@RequestBody Map<String, String> map){
        try{
            subscriptionService.removeSubscription(map.get("post_id"), map.get("user_id"));
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}
