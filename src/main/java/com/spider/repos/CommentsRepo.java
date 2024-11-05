package com.spider.repos;
import com.spider.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepo extends JpaRepository<Comments, Long> {
    List<Comments> findAll();

    List<Comments> findAllByPostId(Long postId);

}
