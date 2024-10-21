package com.spider.controllers;

import com.spider.models.Posts;
import com.spider.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @PostMapping
    public ResponseEntity<Posts> savePosts(@RequestBody Posts post) {
        Posts savePost = postsService.savePost(post);
        return ResponseEntity.ok(savePost);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Posts>> getAllPosts(){
        List<Posts> posts = postsService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Posts>> getPostsById(@PathVariable("id") int id){
        Optional<Posts> userPosts = postsService.getPostById(id);
        return ResponseEntity.ok(userPosts);
    }
}
