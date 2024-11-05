package com.spider.controllers;

import com.spider.models.Posts;
import com.spider.repos.PostsRepo;
import com.spider.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepo postsRepo;

    private final String directory = "uploads";

    @PostMapping
    public ResponseEntity<Posts> savePosts(@RequestBody Posts post) {
        Posts savePost = postsService.savePost(post);
        return ResponseEntity.ok(savePost);
    }

    @PostMapping("/addPost")
    public ResponseEntity<String> addPost(@RequestParam("userId") int userId,
                                          @RequestParam("title") String title,
                                          @RequestParam("content") String content,
                                          @RequestParam("image") MultipartFile image) {
        // Check if all required fields are provided
        if (content == null || image.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing required fields");
        }

        // Create uploads directory if it doesn't exist
        File uploadsDir = new File(directory);
        if (!uploadsDir.exists()) {
            if (uploadsDir.mkdirs()) {
                System.out.println("Uploads directory created successfully");
            } else {
                return ResponseEntity.status(500).body("Failed to create uploads directory");
            }
        } else {
            System.out.println("Uploads directory already exists");
        }

        try {
            // Read the image file and encode it as a byte array
            byte[] imageBytes = image.getBytes();

            // Create a new post instance
            Posts newPost = new Posts();
            newPost.setUser_id(userId);
            newPost.setTitle(title);
            newPost.setContent(content);
            newPost.setCreated(LocalDateTime.now()); // Set current time
            newPost.setImage(imageBytes);

            // Save the post to the database
            postsRepo.save(newPost);

            // Send a success response
            return ResponseEntity.status(201).body("Post created successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to add post");
        }
    }


    @GetMapping("/posts")
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
