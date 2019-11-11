package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/hateoas/users")
@Validated
public class UserHateoasController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    public UserService userService;

    // getAllUsers Method
    @GetMapping
    public Resources<User> getAllUsers() throws UserNotFoundException {
        // implement self-link for user object

        List<User> allUsers = userService.getAllUsers();
        for(User user : allUsers) {

            Long userId = user.getUserid();
            Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
            user.add(selfLink);

            // Relationship link with getALlOrders
            Resources<Order> orders = ControllerLinkBuilder.methodOn(OrderHateoasController.class).getAllOrders(userId);
            Link ordersLink = ControllerLinkBuilder.linkTo(orders).withRel("all-orders");
            user.add(ordersLink);
        }
        // self link for getAllUses
        Link selfLinkgetAllUsers = ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel();
        return new Resources<>(allUsers, selfLinkgetAllUsers);

    }

    // getUserById
    @GetMapping("/{id}")
    public Resource<User> getUserById(@PathVariable("id") @Min(1) Long id) {

        try {
            Optional<User> userOptional = userService.getUserById(id);
            User  user = userOptional.get();
            Long userid = user.getUserid();
            Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
            user.add(selfLink);
            Resource<User> finalResource = new Resource<User>(user);
            return finalResource;

        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}
