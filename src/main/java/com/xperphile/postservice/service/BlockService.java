package com.xperphile.postservice.service;

import com.xperphile.postservice.dao.BlockedPost;
import com.xperphile.postservice.dao.PostContent;
import com.xperphile.postservice.dao.PostMeta;
import com.xperphile.postservice.dto.DisplayPost;
import com.xperphile.postservice.repository.BlockedPostRepository;
import com.xperphile.postservice.repository.PostContentRepository;
import com.xperphile.postservice.repository.PostMetaRepository;
import com.xperphile.postservice.utility.Base64Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class BlockService {

    @Autowired
    private BlockedPostRepository blockedPostRepository;

    @Autowired
    private PostContentRepository postContentRepository;

    @Autowired
    private PostMetaRepository postMetaRepository;

    public void blockPost(String post_id, String user_id) throws IllegalArgumentException{
        if(post_id == null || post_id.isEmpty())
            throw new IllegalArgumentException("Illegal post_id");
        if(user_id == null || user_id.isEmpty())
            throw new IllegalArgumentException("Illegal user_id");
        String id = UUID.randomUUID().toString();
        Timestamp creation_time = new Timestamp(new Date().getTime());
        BlockedPost blockedPost = new BlockedPost(id, post_id, user_id, creation_time);
        blockedPostRepository.save(blockedPost);
    }

    public void unblockPost(String id) throws IllegalArgumentException{
        if(id == null || id.isEmpty())
            throw new IllegalArgumentException("Illegal id");
        blockedPostRepository.deleteById(id);
    }

    public List<DisplayPost> getBlockedPosts(String user_id) throws IllegalArgumentException{
        List<BlockedPost> blockedPosts = blockedPostRepository.findByUser_Id(user_id);
        List<DisplayPost> displayPosts = new ArrayList<>();
        blockedPosts.stream().forEach(blockedPost -> {
            displayPosts.add(getDisplayPost(blockedPost.getPost_id()));
        });
        return displayPosts;
    }

    private DisplayPost getDisplayPost(String post_id) throws IllegalArgumentException{
        if(post_id == null || post_id.isEmpty())
            throw new IllegalArgumentException("Illegal post_id");
        PostMeta postMeta = postMetaRepository.findById(post_id).get();
        PostContent postContent = postContentRepository.findById(post_id).get();
        DisplayPost displayPost = new DisplayPost(post_id, postMeta.getOwner(), Base64Utility.decode(postContent.getContent()), postMeta.getComments(), postMeta.getEmojis(), postMeta.getTags(), postMeta.getTagged_users(), postMeta.getCreation_time(), postMeta.getLatest_modified_time());
        return displayPost;
    }

}
