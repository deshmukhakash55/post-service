package com.xperphile.postservice.controller;

import com.xperphile.postservice.constant.ClientConstants;
import com.xperphile.postservice.service.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ClientConstants.ENGINE_CONTROLLER_MAPPING)
public class EngineController {

    @Autowired
    private EngineService engineService;

    @PostMapping(ClientConstants.CLIENT_GENERATE_RECOMMENDATIONS)
    public ResponseEntity generateRecommendations(@PathVariable("user") String user){
        try{
            engineService.generateRecommendations(user);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        catch (IllegalArgumentException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
