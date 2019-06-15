package com.xperphile.postservice.repository;

import com.xperphile.postservice.dao.PostReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface PostReportRepository extends JpaRepository<PostReport, String> {

    @Query("select c from PostReport c where c.post_id like ?1")
    List<PostReport> findByPost_Id(String post_id);

}
