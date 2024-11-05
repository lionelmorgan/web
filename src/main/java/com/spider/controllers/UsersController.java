package com.spider.controllers;

import com.spider.models.Users;
import com.spider.services.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


/**
 * The controller contains the applications endpoints/ urls.
 * UserController handles HTTP requests concerning users.
 */

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UsersController {

	@Autowired
	private UsersService userService;

//	@PostMapping
//	public ResponseEntity<Users> createUser(@RequestBody Users user) {
//		Users savedUser = userService.saveUser(user);
//		return ResponseEntity.ok(savedUser);
//	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody Users user) {
		try {
			userService.saveUser(user);
			return ResponseEntity.ok("Signup successful");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error signing up");
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Users user) {
		Optional<Users> foundUser = userService.getUserByUsername(user.getUsername());

		if (foundUser.isPresent() && foundUser.get().getPassword().equals(user.getPassword())) {
			return ResponseEntity.ok("Login successful");
		} else {
			return ResponseEntity.status(401).body("Invalid username or password");
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<Users>> getUsers() {
		List<Users> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable Long id) {
		Optional<Users> user = userService.getUserById(id);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<Users> getUserByUsername(@PathVariable String username) {
		Optional<Users> user = userService.getUserByUsername(username);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<Users> getUserByEmail(@PathVariable String email) {
		Optional<Users> user = userService.getUserByEmail(email);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}