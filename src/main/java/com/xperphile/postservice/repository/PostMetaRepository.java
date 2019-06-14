package com.xperphile.postservice.repository;

import com.xperphile.postservice.dao.PostMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface PostMetaRepository extends JpaRepository<PostMeta, String> {

    @Query("select u from PostMeta u where u.tags like %?1%")
    List<PostMeta> findByTagsContaining(String tag);

    @Query("select u from PostMeta u where u.tagged_users like %?1%")
    List<PostMeta> findByTagged_UsersContaining(String tagged_users);

    @Query("select u from PostMeta u order by emojis, creation_time desc")
    List<PostMeta> findAllTop1000OrderByEmojisAndCreation_TimeDesc();

    @Query("select u from PostMeta u where u.owner like %?1% order by creation_time desc")
    List<PostMeta> findByOwnerOrderByCreation_TimeDesc(String owner);

}
