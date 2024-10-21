package com.spider.repos;

import com.spider.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *The repository uses the Java Persistence API (JPA) to access data in the database. JPA essentially maps Java objects to the database table.
*It references the Users model to access an object of Users.
*We are making the data type below <Users> since we are looking for a user object to be returned. The object returned will be an
*array of information about the user including user_id, username, email, profile picture, bio, etc.
 */

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
    List<Users> findAll();
}

