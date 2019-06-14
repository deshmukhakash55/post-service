package com.xperphile.postservice.service;

import com.xperphile.postservice.dao.PostSubscription;
import com.xperphile.postservice.repository.PostSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Component
public class SubscriptionService {

    @Autowired
    private PostSubscriptionRepository postSubscriptionRepository;

    public void addSubscription(String post_id, String user)throws IllegalArgumentException{
        if(post_id == null || post_id.isEmpty())
            throw new IllegalArgumentException("Illegal post_id");
        if(user == null || user.isEmpty())
            throw new IllegalArgumentException("Illegal user");
        String id = UUID.randomUUID().toString();
        Timestamp creation_time = new Timestamp(new Date().getTime());
        PostSubscription postSubscription = new PostSubscription(id, post_id, user, creation_time);
        postSubscriptionRepository.save(postSubscription);
    }

    public void removeSubscription(String post_id, String user) throws IllegalArgumentException{
        if(post_id == null || post_id.isEmpty())
            throw new IllegalArgumentException("Illegal post_id");
        if(user == null || user.isEmpty())
            throw new IllegalArgumentException("Illegal user");
        PostSubscription postSubscription = postSubscriptionRepository.findByPost_IdAndUser(post_id, user).get(0);
        postSubscriptionRepository.deleteById(postSubscription.getId());
    }

}
