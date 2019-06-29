package com.xperphile.postservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xperphile.postservice.constant.ClientConstants;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


@Component
public class PostServiceListener {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${funnelmessage.exchange}")
    private String funnelMessageExchange;

    @Value("${funnelmessage.routingkey}")
    private String funnelMessageRoutingKey;

    @Autowired
    private Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    @RabbitListener(queues = {"postmessage.queue"})
    public void receiveMessage(Map<String, Object> message) {

        Map<String, HttpMethod> methodMap = new HashMap<>();
        methodMap.put("GET", HttpMethod.GET);
        methodMap.put("POST", HttpMethod.POST);
        methodMap.put("DELETE", HttpMethod.DELETE);
        methodMap.put("PUT", HttpMethod.PUT);

        try{
            ObjectMapper mapper = new ObjectMapper();

            File mappingFile = ResourceUtils.getFile("classpath:" + ClientConstants.MESSAGE_TO_MAPPING_FILE);
            Map<String, String> mappings = (Map<String, String>)mapper.readValue(mappingFile, Map.class);

            File funnelMessageFile = ResourceUtils.getFile("classpath:" + ClientConstants.POSTMESSAGE_TO_FUNNELMESSAGE_FILE);
            Map<String, String> funnelMessageMappings = (Map<String, String>)mapper.readValue(funnelMessageFile, Map.class);

            File funnelMethodFile = ResourceUtils.getFile("classpath:" + ClientConstants.POSTMESSAGE_TO_FUNNELMETHOD_FILE);
            Map<String, String> funnelMethodMappings = (Map<String, String>)mapper.readValue(funnelMethodFile, Map.class);

            String pathVariables = "";
            if(message.containsKey("pathVariables"))
                pathVariables = message.get("pathVariables").toString();
            final String uri = "http://localhost:" + environment.getProperty("server.port") + mappings.get(message.get("message")) + pathVariables;
            HttpEntity<Object> entity = new HttpEntity<>(message.get("requestEntity"));

            ResponseEntity<Object> responseEntity = restTemplate.exchange(uri, methodMap.get(message.get("method")), entity, new ParameterizedTypeReference<Object>() {
            });

            Object object = responseEntity.getBody();

            if(object != null){
                Map<String, Object> map = new HashMap<>();
                map.put("message", funnelMessageMappings.get(message.get("message")));
                map.put("method", funnelMethodMappings.get(message.get("message")));
                map.put("requestEntity", object);
                amqpTemplate.convertAndSend(funnelMessageExchange, funnelMessageRoutingKey, map);
            }
        }
        catch (Exception exception){
            // todo log the exception
        }
    }

}
