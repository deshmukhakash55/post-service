package com.xperphile.postservice.service;

import com.xperphile.postservice.constant.ClientConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Component
public class EngineService {

    @Autowired
    private RestTemplate restTemplate;

    public void generateRecommendations(String user) throws IllegalArgumentException{
        if(user == null || user.isEmpty())
            throw new IllegalArgumentException("Illegal user");
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(Collections.singletonMap("user", user));
        restTemplate.exchange(ClientConstants.POST_ENGINE_URL, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<Object>(){});
    }

}
