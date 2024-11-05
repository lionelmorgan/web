package com.spider.controllers;

import com.spider.models.Events;
import com.spider.repos.EventsRepo;
import com.spider.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @Autowired
    private EventsRepo eventsRepo;

    private final String directory = "uploads"; // Directory to save uploaded images

    @PostMapping("/addEvent")
    public ResponseEntity<String> addEvent(
            @RequestParam("event_name") String eventName,
            @RequestParam("event_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventDate,
            @RequestParam("location") String location,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile image) {

        // Check if all required fields are provided
        if (eventName == null || eventDate == null || location == null || description == null || image.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing required fields");
        }

        // Create uploads directory if it doesn't exist
        File uploadsDir = new File(directory);
        if (!uploadsDir.exists() && !uploadsDir.mkdirs()) {
            return ResponseEntity.status(500).body("Failed to create uploads directory");
        }

        try {
            // Read the image file and encode it as a byte array
            byte[] imageBytes = image.getBytes();

            // Create a new event instance
            Events newEvent = new Events();
            newEvent.setEvent_name(eventName);
            newEvent.setEvent_date(eventDate);
            newEvent.setLocation(location);
            newEvent.setDescription(description);
            newEvent.setCreated(LocalDateTime.now()); // Set current time
            newEvent.setImage(imageBytes); // Set the image bytes

            // Save the event to the database
            eventsRepo.save(newEvent);

            // Send a success response
            return ResponseEntity.status(201).body("Event created successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to add event");
        }
    }

    @GetMapping("/events")
    public ResponseEntity<List<Events>> getAllEvents() {
        List<Events> events = eventsService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Events>> getEventById(@PathVariable("id") int id) {
        Optional<Events> event = eventsService.getEventById(id);
        return ResponseEntity.ok(event);
    }
}
