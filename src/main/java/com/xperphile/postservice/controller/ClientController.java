package com.xperphile.postservice.controller;

import com.xperphile.postservice.constant.ClientConstants;
import com.xperphile.postservice.dto.Post;
import com.xperphile.postservice.service.ClientService;
import com.xperphile.postservice.utility.HttpEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ClientConstants.CLIENT_CONTROLLER_MAPPING)
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping(ClientConstants.CLIENT_ADD_POSTS)
    public HttpEntityResponse addPost(@RequestBody Post post){
        try{
            clientService.addPost(post);
            return new HttpEntityResponse(HttpStatus.ACCEPTED);
        }
        catch(IllegalArgumentException exception){
            return new HttpEntityResponse(HttpStatus.BAD_REQUEST);
        }
    }

}
