package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.mappers.UserMapper;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController
{
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserMsDto> getAllUserDtos() {
        return userMapper.userToUserDtos(userRepo.findAll());
    }

    @GetMapping("/{id}")
    public UserMsDto getUserById(@PathVariable("id") Long id){
        Optional<User> userOptional = userRepo.findById(id);
        User user = userOptional.get();
        return userMapper.userToUserDto(user);
    }

}
