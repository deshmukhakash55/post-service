package com.xperphile.postservice.service;

import com.xperphile.postservice.constant.ClientConstants;
import com.xperphile.postservice.dao.Comment;
import com.xperphile.postservice.dao.PostContent;
import com.xperphile.postservice.dao.PostMeta;
import com.xperphile.postservice.dao.PostRecommendations;
import com.xperphile.postservice.dto.DisplayPost;
import com.xperphile.postservice.dto.Post;
import com.xperphile.postservice.dto.RawComment;
import com.xperphile.postservice.dto.Recommendation;
import com.xperphile.postservice.repository.CommentRepository;
import com.xperphile.postservice.repository.PostContentRepository;
import com.xperphile.postservice.repository.PostMetaRepository;
import com.xperphile.postservice.repository.PostRecommedationRepository;
import com.xperphile.postservice.utility.Base64Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

@Component
public class ClientService {

    @Autowired
    PostContentRepository postContentRepository;

    @Autowired
    PostMetaRepository postMetaRepository;

    @Autowired
    PostRecommedationRepository postRecommedationRepository;

    @Autowired
    CommentRepository commentRepository;

    public void addPost(Post post) throws IllegalArgumentException{
        if(post == null)
            throw new IllegalArgumentException("Invalid Post");
        String id = UUID.randomUUID().toString();
        Timestamp current_timestamp = new Timestamp(new Date().getTime());
        PostContent postContent = new PostContent(id, Base64Utility.decode(post.getContent()));
        postContentRepository.save(postContent);
        PostMeta postMeta = new PostMeta(id, post.getOwner_id(), current_timestamp, current_timestamp, new BigInteger("0"), new BigInteger("0"), "", "");
        postMetaRepository.save(postMeta);
    }

    public void addTagToPost(String post_id, String tag) throws IllegalArgumentException{
        if(tag == null || tag.isEmpty())
            throw new IllegalArgumentException("Illegal tag");
        PostMeta postMeta = postMetaRepository.findById(post_id).get();
        String tags = postMeta.getTags();
        if(!tags.isEmpty())
            tags = tags.concat(ClientConstants.DELIMITER);
        tags = tags.concat(tag);
        postMeta.setTags(tags);
        postMetaRepository.save(postMeta);
    }

    public void removeTagFromPost(String post_id, String tag) throws IllegalArgumentException{
        if(tag == null || tag.isEmpty())
            throw new IllegalArgumentException("Illegal tag");
        PostMeta postMeta = postMetaRepository.findById(post_id).get();
        String tags = postMeta.getTags();
        String updatedTags = "";
        StringTokenizer tokenizer = new StringTokenizer(tags, ClientConstants.DELIMITER);
        while (tokenizer.hasMoreElements()){
            String currentTag = tokenizer.nextElement().toString();
            if(currentTag.equalsIgnoreCase(tag))
                continue;
            updatedTags = updatedTags.concat(currentTag + ClientConstants.DELIMITER);
        }
        postMeta.setTags(updatedTags);
        postMetaRepository.save(postMeta);
    }

    public void removeTaggedUserFromPost(String post_id, String tagged_user) throws IllegalArgumentException{
        if(tagged_user == null || tagged_user.isEmpty())
            throw new IllegalArgumentException("Illegal user");
        PostMeta postMeta = postMetaRepository.findById(post_id).get();
        String taggedUsers = postMeta.getTagged_users();
        String updateTaggedUsers = "";
        StringTokenizer tokenizer = new StringTokenizer(taggedUsers, ClientConstants.DELIMITER);
        while (tokenizer.hasMoreElements()){
            String currentTaggedUser = tokenizer.nextElement().toString();
            if(currentTaggedUser.equalsIgnoreCase(tagged_user)) {
                removeRecommendation(post_id, tagged_user);
                continue;
            }
            updateTaggedUsers = updateTaggedUsers.concat(currentTaggedUser + ClientConstants.DELIMITER);
        }
        postMeta.setTagged_users(updateTaggedUsers);
        postMetaRepository.save(postMeta);
    }

    public void removeRecommendation(String post_id, String user_id) throws IllegalArgumentException {
        if(post_id == null || post_id.isEmpty())
            throw new IllegalArgumentException("Illegal post_id");
        if(user_id == null || user_id.isEmpty())
            throw new IllegalArgumentException("Illegal tagged_user");
        PostRecommendations postRecommendations = postRecommedationRepository.findByPost_IdAndUser_Id(post_id, user_id).get(0);
        if(postRecommendations == null)
            throw new IllegalArgumentException("No such post exists");
        postRecommedationRepository.deleteById(postRecommendations.getId());
    }

    public void addTaggedUserToPost(String post_id, String tagged_user) throws IllegalArgumentException{
        if(tagged_user == null || tagged_user.isEmpty())
            throw new IllegalArgumentException("Illegal user");
        PostMeta postMeta = postMetaRepository.findById(post_id).get();
        String taggedUsers = postMeta.getTagged_users();
        if(!taggedUsers.isEmpty())
            taggedUsers = taggedUsers.concat(ClientConstants.DELIMITER);
        taggedUsers = taggedUsers.concat(tagged_user);
        postMeta.setTagged_users(taggedUsers);
        postMetaRepository.save(postMeta);
        String status = addRecommendation(new Recommendation(post_id, tagged_user, "" + ClientConstants.TAGGED, postMeta.getOwner()));
    }

    public void updatePost(Post post, String post_id) throws IllegalArgumentException{
        PostContent postContent = new PostContent(post_id, Base64Utility.decode(post.getContent()));
        Optional<PostMeta> optionalPostMeta = postMetaRepository.findById(post_id);
        if(!optionalPostMeta.isPresent())
            throw new IllegalArgumentException("Post doesn't exists");
        PostMeta postMeta = postMetaRepository.findById(post_id).get();
        postMeta.setLatest_modified_time(new Timestamp(new Date().getTime()));
        postContentRepository.save(postContent);
        postMetaRepository.save(postMeta);
    }

    public void addCommentOrEmoji(RawComment rawComment) throws IllegalArgumentException{
        if(!isValidRawComment(rawComment))
            throw new IllegalArgumentException("Illegal Raw Comment");
        String id = UUID.randomUUID().toString();
        Comment comment = new Comment(id, rawComment.getPost_id(), rawComment.getUser(), Base64Utility.decode(rawComment.getComment_content()), rawComment.getComment_type(), rawComment.getReferred_comment());
        commentRepository.save(comment);
        PostMeta postMeta = postMetaRepository.findById(rawComment.getPost_id()).get();
        int comment_type = Integer.parseInt(rawComment.getComment_type());
        if(comment_type == ClientConstants.DIRECT_COMMENT){
            BigInteger comments = postMeta.getComments();
            comments = comments.add(new BigInteger("1"));
            postMeta.setComments(comments);
        }
        else if(comment_type == ClientConstants.EMOJI){
            BigInteger emojis = postMeta.getEmojis();
            emojis = emojis.add(new BigInteger("1"));
            postMeta.setComments(emojis);
        }
        postMetaRepository.save(postMeta);
    }

    public String addRecommendation(Recommendation recommendation) throws IllegalArgumentException{
        if(!isValidRecommendation(recommendation))
            throw new IllegalArgumentException("Illegal Recommendation");
        String message = "";
        PostRecommendations postRecommendations = postRecommedationRepository.findByPost_Id(recommendation.getPost_id()).get(0);
        if(postRecommendations == null){
            String id = UUID.randomUUID().toString();
            Date date = new Date();
            Timestamp creation_time = new Timestamp(date.getTime());
            Timestamp expiry_time = null;
            if(Integer.parseInt(recommendation.getSource_type()) == ClientConstants.RECOMMENDATION_FROM_ENGINE) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DATE, 1);
                date = calendar.getTime();
                expiry_time = new Timestamp(date.getTime());
            }
            postRecommendations = new PostRecommendations(id, recommendation.getPost_id(), recommendation.getUser_id(), recommendation.getSource_type(), recommendation.getSource(), creation_time, expiry_time);
        }
        else{
            int current_source_type = Integer.parseInt(postRecommendations.getSource_type());
            int newer_source_type = Integer.parseInt(recommendation.getSource_type());
            if(current_source_type == ClientConstants.RECOMMENDATION_FROM_USER && newer_source_type == ClientConstants.RECOMMENDATION_FROM_ENGINE)
                message = "Already recommended by some user";
            else if(current_source_type == ClientConstants.RECOMMENDATION_FROM_ENGINE && newer_source_type == ClientConstants.RECOMMENDATION_FROM_USER){
                postRecommendations.setExpiry_time(null);
                postRecommendations.setCreation_time(new Timestamp(new Date().getTime()));
                postRecommendations.setSource_type("" + ClientConstants.RECOMMENDATION_FROM_USER);
                postRecommendations.setSource(recommendation.getSource());
                message = "Recommended Successfully";
            }
            else if(current_source_type == ClientConstants.RECOMMENDATION_FROM_ENGINE && newer_source_type == ClientConstants.RECOMMENDATION_FROM_ENGINE){
                postRecommendations.setExpiry_time(null);
                postRecommendations.setCreation_time(new Timestamp(new Date().getTime()));
                message = "Recommended Successfully";
            }
            else if(current_source_type == ClientConstants.RECOMMENDATION_FROM_USER && newer_source_type == ClientConstants.RECOMMENDATION_FROM_USER){
                String id = UUID.randomUUID().toString();
                Date date = new Date();
                Timestamp creation_time = new Timestamp(date.getTime());
                Timestamp expiry_time = null;
                postRecommendations = new PostRecommendations(id, recommendation.getPost_id(), recommendation.getUser_id(), recommendation.getSource_type(), recommendation.getSource(), creation_time, expiry_time);
                message = "Recommended Successfully";
            }
            else if(current_source_type == ClientConstants.TAGGED && newer_source_type == ClientConstants.TAGGED){
                message = "Already tagged";
            }
            else if(current_source_type == ClientConstants.TAGGED && newer_source_type == ClientConstants.TAGGED){
                String id = UUID.randomUUID().toString();
                Date date = new Date();
                Timestamp creation_time = new Timestamp(date.getTime());
                Timestamp expiry_time = null;
                postRecommendations = new PostRecommendations(id, recommendation.getPost_id(), recommendation.getUser_id(), recommendation.getSource_type(), recommendation.getSource(), creation_time, expiry_time);
                message = "Recommended Successfully";
            }
        }
        postRecommedationRepository.save(postRecommendations);
        return message;
    }

    public List<DisplayPost> getRecommendedPosts(String user){
        List<DisplayPost> recommendedPosts = new ArrayList<>();
        List<PostRecommendations> postRecommendations = postRecommedationRepository.findAllByUser_Id(user);
        postRecommendations.stream().forEach(recommendedPost -> {
            String post_id = recommendedPost.getPost_id();
            PostContent postContent = postContentRepository.findById(post_id).get();
            PostMeta postMeta = postMetaRepository.findById(post_id).get();
            DisplayPost displayPost = new DisplayPost(post_id, postMeta.getOwner(), Base64Utility.encode(postContent.getContent()), postMeta.getComments(), postMeta.getEmojis(), postMeta.getTags(), postMeta.getTagged_users(), postMeta.getCreation_time(), postMeta.getLatest_modified_time());
            recommendedPosts.add(displayPost);
        });
        return recommendedPosts;
    }

    public List<DisplayPost> getUserPosts(String user){
        List<DisplayPost> posts = new ArrayList<>();
        List<PostMeta> postMetas = postMetaRepository.findByOwnerOrderByCreation_TimeDesc(user);
        postMetas.stream().forEach(postMeta -> {
            String post_id = postMeta.getId();
            PostContent postContent = postContentRepository.findById(post_id).get();
            DisplayPost displayPost = new DisplayPost(post_id, postMeta.getOwner(), Base64Utility.encode(postContent.getContent()), postMeta.getComments(), postMeta.getEmojis(), postMeta.getTags(), postMeta.getTagged_users(), postMeta.getCreation_time(), postMeta.getLatest_modified_time());
            posts.add(displayPost);
        });
        return posts;
    }

    private boolean isValidRecommendation(Recommendation recommendation) {
        boolean isValid = true;
        try{
            int source_type = Integer.parseInt(recommendation.getSource_type());
            if(source_type > 1)
                isValid = false;
        }
        catch (NumberFormatException numberFormatException){
            isValid = false;
        }
        return isValid;
    }

    private boolean isValidRawComment(RawComment rawComment) {
        boolean isValid = true;
        try {
            int comment_type = Integer.parseInt(rawComment.getComment_type());
            if(comment_type > 2)
                isValid = false;
            if(comment_type == ClientConstants.EMOJI || comment_type == ClientConstants.REPLY){
                if(rawComment.getReferred_comment().isEmpty() || rawComment.getReferred_comment() == null)
                    isValid = false;
            }
        }
        catch (NumberFormatException numberFormatException){
            isValid = false;
        }
        return isValid;
    }

}
