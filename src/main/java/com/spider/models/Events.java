package com.spider.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_id;

    @Column(nullable = false)
    private String event_name;

    @Column(nullable = false)
    private LocalDateTime event_date;

    @Column
    private String location;

    @Column(length = 500) // Adjust the length as needed
    private String description;

    @Column
    private LocalDateTime created;

    @Column
    private byte[] image; // Add this line for image storage

    public Events() {
    }

    public Events(int post_id, String event_name, LocalDateTime event_date, String location, String description, byte[] image) {
        this.post_id = post_id;
        this.event_name = event_name;
        this.event_date = event_date;
        this.location = location;
        this.description = description;
        this.created = LocalDateTime.now(); // Automatically set to current time
        this.image = image; // Initialize image
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public LocalDateTime getEvent_date() {
        return event_date;
    }

    public void setEvent_date(LocalDateTime event_date) {
        this.event_date = event_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public byte[] getImage() { // Getter for image
        return image;
    }

    public void setImage(byte[] image) { // Setter for image
        this.image = image;
    }
}
