package com.spider.services;

import com.spider.models.Users;
import com.spider.repos.UsersRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The Service contains the application's business logic
 */
@Service
public class UsersService {

	private static final Logger logger = LogManager.getLogger(UsersService.class);

	@Autowired
	private UsersRepo userRepository;

	public Users saveUser(Users user) {
//		String pwd = user.getPassword();
//		user.setPassword(BCrypt.hashpw(pwd, BCrypt.gensalt()));
		Users savedUser = userRepository.save(user);
		logger.info("User saved: {}", savedUser);
		return savedUser;
	}

	public List<Users> getAllUsers() {
		List<Users> users = userRepository.findAll();
		logger.info("Retrieved all users, count: {}", users.size());
		return users;
	}

	public Optional<Users> getUserById(Long id) {
		Optional<Users> user = userRepository.findById(id);
		if (user.isPresent()) {
			logger.info("User found by ID: {}", id);
		} else {
			logger.warn("No user found with ID: {}", id);
		}
		return user;
	}

	public Optional<Users> getUserByUsername(String username) {
		Optional<Users> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			logger.info("User found by username: {}", username);
		} else {
			logger.warn("No user found with username: {}", username);
		}
		return user;
	}

	public Optional<Users> getUserByEmail(String email) {
		Optional<Users> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			logger.info("User found by email: {}", email);
		} else {
			logger.warn("No user found with email: {}", email);
		}
		return user;
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
		logger.info("User deleted with ID: {}", id);
	}
}