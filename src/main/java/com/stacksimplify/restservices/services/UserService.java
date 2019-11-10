package com.stacksimplify.restservices.services;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserAlreadyExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	// auto-wire UserRepository
	@Autowired
	private UserRepository userRepo;

	// getAllUsers method
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public User createUser(User user) throws UserAlreadyExistsException {
		User userExists = userRepo.findByUsername(user.getUsername());
		if (userExists != null)
			throw new UserAlreadyExistsException("User already exists; createUser method ");
		return userRepo.save(user);
	}

	public Optional<User> getUserById(long id) throws UserNotFoundException {
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("User not found for id: " + id);
		return user;
	}

	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> user1 = userRepo.findById(id);
		if (!user1.isPresent())
			throw new UserNotFoundException("User not found in in the repo, please provide the corect user id");
		user.setId(id);
		return userRepo.save(user);
	}

	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public void deleteByUserId(Long id) {
		if (userRepo.findById(id).isPresent())
			userRepo.deleteById(id);
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in repo, delete can't be done");
	}
}
