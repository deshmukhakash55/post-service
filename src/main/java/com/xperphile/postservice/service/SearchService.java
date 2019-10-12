package com.xperphile.postservice.service;

import com.xperphile.postservice.dao.PostContent;
import com.xperphile.postservice.dao.PostMeta;
import com.xperphile.postservice.dto.DisplayPost;
import com.xperphile.postservice.repository.PostContentRepository;
import com.xperphile.postservice.repository.PostMetaRepository;
import com.xperphile.postservice.repository.PostRecommedationRepository;
import com.xperphile.postservice.repository.PostSubscriptionRepository;
import com.xperphile.postservice.utility.Base64Utility;
import com.xperphile.postservice.utility.BoyerMoore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchService {

    @Autowired
    private PostContentRepository postContentRepository;

    @Autowired
    private PostMetaRepository postMetaRepository;

    @Autowired
    private PostSubscriptionRepository postSubscriptionRepository;

    @Autowired
    private PostRecommedationRepository postRecommedationRepository;

    public List<DisplayPost> searchPostsByTag(String tag){
        List<PostMeta> postMetaList = postMetaRepository.findByTagsContaining(tag);
        List<DisplayPost> posts = new ArrayList<>();
        for(PostMeta postMeta : postMetaList){
            PostContent postContent = postContentRepository.findById(postMeta.getId()).get();
            DisplayPost post = new DisplayPost(postMeta.getId(), postMeta.getOwner(), Base64Utility.decode(postContent.getContent()), postMeta.getComments(), postMeta.getEmojis(), postMeta.getTags(), postMeta.getTagged_users(), postMeta.getCreation_time(), postMeta.getLatest_modified_time(), postMeta.getName());
            posts.add(post);
        }
        return posts;
    }

    public List<DisplayPost> searchPostsByTaggedUser(String tagged_users){
        List<PostMeta> postMetaList = postMetaRepository.findByTagged_UsersContaining(tagged_users);
        List<DisplayPost> posts = new ArrayList<>();
        for(PostMeta postMeta : postMetaList){
            PostContent postContent = postContentRepository.findById(postMeta.getId()).get();
            DisplayPost post = new DisplayPost(postMeta.getId(), postMeta.getOwner(), Base64Utility.decode(postContent.getContent()), postMeta.getComments(), postMeta.getEmojis(), postMeta.getTags(), postMeta.getTagged_users(), postMeta.getCreation_time(), postMeta.getLatest_modified_time(), postMeta.getName());
            posts.add(post);
        }
        return posts;
    }

    public List<DisplayPost> searchPostsBySearchedText(String searchText){
        List<DisplayPost> posts = new ArrayList<>();
        List<PostMeta> postMetas = postMetaRepository.findAllTop1000OrderByEmojisAndCreation_TimeDesc();
        for(PostMeta postMeta : postMetas){
            PostContent postContent = postContentRepository.findById(postMeta.getId()).get();
            if(BoyerMoore.indexOf(Base64Utility.decode(postContent.getContent()), searchText.getBytes()) != -1){
                DisplayPost post = new DisplayPost(postMeta.getId(), postMeta.getOwner(), Base64Utility.decode(postContent.getContent()), postMeta.getComments(), postMeta.getEmojis(), postMeta.getTags(), postMeta.getTagged_users(), postMeta.getCreation_time(), postMeta.getLatest_modified_time(), postMeta.getName());
                posts.add(post);
            }
        }
        return posts;
    }

}
