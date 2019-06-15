package com.xperphile.postservice.repository;

import com.xperphile.postservice.dao.PostContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface PostContentRepository extends JpaRepository<PostContent, String> {

    @Query("SELECT c.content from PostContent c where c.id like ?1")
    String findContentById(String id);

}
