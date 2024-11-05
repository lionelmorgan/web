package com.spider.services;

import com.spider.models.Posts;
import com.spider.models.Users;
import com.spider.repos.PostsRepo;
import com.spider.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostsService {

    @Autowired
    private PostsRepo postsRepo;

    @Autowired
    private UsersRepo usersRepo;

    public Posts savePost(Posts post){
        return postsRepo.save(post);
    }

    public List<Posts> getAllPosts(){
        return postsRepo.findAll();
    }

    public Optional<Posts> getPostById(int id){
        return postsRepo.findById(id);
    }

}
