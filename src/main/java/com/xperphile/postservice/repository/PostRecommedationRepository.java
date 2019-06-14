package com.xperphile.postservice.repository;

import com.xperphile.postservice.dao.PostRecommendations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRecommedationRepository extends JpaRepository<PostRecommendations, String> {

    @Query("select c from PostRecommendations c where c.post_id like ?1")
    List<PostRecommendations> findByPost_Id(String post_id);

    @Query("select c from PostRecommendations c where c.user_id like ?1")
    List<PostRecommendations> findAllByUser_Id(String user_id);

    @Query("select c from PostRecommendations c where c.post_id like ?1 and user_id like ?2")
    List<PostRecommendations> findByPost_IdAndUser_Id(String post_id, String user_id);

}
