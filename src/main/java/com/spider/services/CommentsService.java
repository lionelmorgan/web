package com.spider.services;

import com.spider.models.Comments;
import com.spider.repos.CommentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepo commentsRepo;

    public Comments createComment(Comments comment) {
        return commentsRepo.save(comment);
    }

    public List<Comments> getCommentsByPostId(Long postId) {
        return commentsRepo.findAllByPostId(postId);
    }

    public Optional<Comments> getComment(Long id) {
        return commentsRepo.findById(id);
    }
}
