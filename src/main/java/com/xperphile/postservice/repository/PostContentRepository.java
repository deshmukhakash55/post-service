package com.xperphile.postservice.repository;

import com.xperphile.postservice.dao.PostContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface PostContentRepository extends JpaRepository<PostContent, String> {

}
