package com.spider.services;

import com.spider.models.Events;
import com.spider.models.Users;
import com.spider.repos.EventsRepo;
import com.spider.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventsService {

    @Autowired
    private EventsRepo eventsRepo;

    @Autowired
    private UsersRepo usersRepo;

    public Events saveEvent(Events event) {
        return eventsRepo.save(event);
    }

    public List<Events> getAllEvents() {
        return eventsRepo.findAll();
    }

    public Optional<Events> getEventById(int id) {
        return eventsRepo.findById(id);
    }

    public void deleteEvent(int id) {
        eventsRepo.deleteById(id); // Optional: Add error handling or checks
    }
}
