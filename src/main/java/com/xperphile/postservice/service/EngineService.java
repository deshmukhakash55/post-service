package com.xperphile.postservice.service;

import com.xperphile.postservice.constant.ClientConstants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class EngineService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void generateRecommendations(String user) throws IllegalArgumentException{
        if(user == null || user.isEmpty())
            throw new IllegalArgumentException("Illegal user");
        amqpTemplate.convertAndSend(ClientConstants.POST_ENGINE_EXCHANGE, ClientConstants.POST_ENGINE_ROUTINGKEY, Collections.singletonMap("user", user));
    }

}
