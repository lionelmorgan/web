package com.spider.controllers;

import com.spider.models.Comments;
import com.spider.models.Posts;
import com.spider.repos.CommentsRepo;
import com.spider.repos.PostsRepo;
import com.spider.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommentsRepo commentsRepo;

    @Autowired
    private PostsRepo postsRepo;

    @PostMapping
    public ResponseEntity<Comments> addComment(@RequestBody Comments commentRequest) {
        try {
            // Fetch the Post entity using the postId from the request
            Posts post = postsRepo.findById(commentRequest.getPost().getId())
                    .orElseThrow(() -> new RuntimeException("Post not found"));

            // Set the post object into the comment
            commentRequest.setPost(post);

            // Use the service to create the comment
            Comments savedComment = commentsService.createComment(commentRequest);

            // Return the saved comment as a response
            return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
        } catch (Exception e) {
            // Handle errors (e.g., Post not found, etc.)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{postId}")
    public List<Comments> getCommentsByPostId(@PathVariable Long postId) {
        return commentsService.getCommentsByPostId(postId);
    }

    @GetMapping("/{id}")
    public Optional<Comments> getComment(@PathVariable Long id) {
        return commentsService.getComment(id);
    }
}

