package com.xperphile.postservice.repository;

import com.xperphile.postservice.dao.BlockedPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface BlockedPostRepository extends JpaRepository<BlockedPost, String> {

    @Query("select c from BlockedPost c where c.user_id like ?1")
    List<BlockedPost> findByUser_Id(String user_id);

}
