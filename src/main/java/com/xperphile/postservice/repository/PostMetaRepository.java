package com.xperphile.postservice.repository;

import com.xperphile.postservice.dao.PostMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface PostMetaRepository extends JpaRepository<PostMeta, String> {

    List<PostMeta> findByTagsContaining(String tag);

    List<PostMeta> findByTagged_UsersContaining(String tagged_users);

    List<PostMeta> findAllTop1000OrderByEmojisAndCreation_TimeDesc();

}
