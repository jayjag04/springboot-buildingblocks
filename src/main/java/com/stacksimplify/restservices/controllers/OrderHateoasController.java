package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/hateoas/users")
public class OrderHateoasController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private OrderRepository orderRepo;

    @GetMapping("/{userid}/orders")
    Resources<Order> getAllOrders(@PathVariable("userid") Long id) throws UserNotFoundException {
        Optional<User> userOptional =  userRepo.findById(id);
        if(!userOptional.isPresent()) throw new UserNotFoundException("User not found for " + id);
        List<Order> allOrders = userOptional.get().getOrders();
        Resources<Order> finalResources = new Resources<Order>(allOrders);
        return finalResources;
    }
}
