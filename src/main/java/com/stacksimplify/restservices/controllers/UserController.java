package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.services.UserService;

@RestController
public class UserController {
	
	// autowire user service
	@Autowired	
	private UserService userSvc;
	
	@GetMapping("/users")
	private List<User> getAllUsers() {
		return userSvc.getAllUsers();
	}
	
	@PostMapping("/users")
	public 	User createUser(@RequestBody User user) {
		return userSvc.createUser(user);
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id){
		
		return userSvc.getUserById(id);
		
	}
	@PutMapping("/users/{id}")
	public User updateUserById(@RequestBody User user, @PathVariable("id") Long id) {
		return userSvc.updateUserById(id, user);
	}
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userSvc.deleteByUserId(id);
	}
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userSvc.getUserByUsername(username);
	}
}
