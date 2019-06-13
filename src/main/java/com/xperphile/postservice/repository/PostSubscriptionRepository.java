package com.xperphile.postservice.repository;

import com.xperphile.postservice.dao.PostSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostSubscriptionRepository extends JpaRepository<PostSubscription, String> {

    PostSubscription findOneByPost_IdAndUser(String post_id, String user);

}
