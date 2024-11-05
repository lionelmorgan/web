package com.spider.repos;

import com.spider.models.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventsRepo extends JpaRepository<Events, Integer> {

    List<Events> findAll();

    Optional<Events> findById(int id);


}
