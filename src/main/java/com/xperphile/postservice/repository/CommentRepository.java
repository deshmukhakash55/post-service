package com.xperphile.postservice.repository;

import com.xperphile.postservice.dao.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface CommentRepository extends JpaRepository<Comment, String> {
}
