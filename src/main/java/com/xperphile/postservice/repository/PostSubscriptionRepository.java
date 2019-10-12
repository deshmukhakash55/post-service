package com.xperphile.postservice.repository;

import com.xperphile.postservice.dao.PostSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostSubscriptionRepository extends JpaRepository<PostSubscription, String> {

    @Query("select c from PostSubscription c where c.post_id like ?1 and user like ?2")
    List<PostSubscription> findByPost_IdAndUser(String post_id, String user);

}
