package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {

	// autowire UserRepository
	@Autowired
	private UserRepository userRepo;
	
	// getAllUsers method
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	public User createUser(User user) {
		return userRepo.save(user);
	}
	
	public Optional<User> getUserById(long id) {
		Optional<User> user = userRepo.findById(id);
		return user; 
	}
	
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepo.save(user);
	}
	
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public void deleteByUserId(Long id) {
		if(userRepo.findById(id).isPresent())
		userRepo.deleteById(id);
	}
}
