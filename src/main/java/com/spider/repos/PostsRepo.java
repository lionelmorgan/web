package com.spider.repos;

import com.spider.models.Posts;
import com.spider.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PostsRepo extends JpaRepository<Posts, Integer> {

    List<Posts> findAll();

    Optional<Posts> findById(int id);




}
