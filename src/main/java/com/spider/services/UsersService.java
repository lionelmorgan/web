package com.spider.services;

import com.spider.models.Users;
import com.spider.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

	@Autowired
	private UsersRepo userRepository;

	public Users saveUser(Users user) {
		return userRepository.save(user);
	}

	public List<Users> getAllUsers(){
		return userRepository.findAll();
	}

	public Optional<Users> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public Optional<Users> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Optional<Users> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	// Additional business logic methods can be added here
}