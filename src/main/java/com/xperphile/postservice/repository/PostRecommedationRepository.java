package com.xperphile.postservice.repository;

import com.xperphile.postservice.dao.PostRecommendations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRecommedationRepository extends JpaRepository<PostRecommendations, String> {

    PostRecommendations findOneByPost_Id(String post_id);

    List<PostRecommendations> findAllByUser_Id(String user_id);

    PostRecommendations findOneByPost_IdAndUser_Id(String post_id, String user_id);

}
