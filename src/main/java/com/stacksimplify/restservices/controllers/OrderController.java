package com.stacksimplify.restservices.controllers;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/users")
public class OrderController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    OrderRepository orderRepo;

@GetMapping("/{userid}/orders")
    private List<Order> getAllOrders(@PathVariable("userid") Long id) throws UserNotFoundException {
        Optional<User> userOptional =  userRepo.findById(id);
        if(!userOptional.isPresent()) throw new UserNotFoundException("User not found for " + id);
        return userOptional.get().getOrders();
    }

    @PostMapping("/{userid}/orders")
    private void createOrder(@PathVariable("userid") Long userid, @RequestBody Order order) throws UserNotFoundException {
        Optional<User> userOptional = userRepo.findById(userid);
        if(!userOptional.isPresent()) throw new UserNotFoundException("User not found for " + userid);
        order.setUser(userOptional.get());
        orderRepo.save(order);
    }

    @GetMapping("/{userid}/orders/{orderid}")
    private Order getOrderByOrderId(@PathVariable("userid") Long userid, @PathVariable("orderid") Long orderid ) throws UserNotFoundException, OrderNotFoundException {
        Optional<User> userOptional = userRepo.findById(userid);
        if(!userOptional.isPresent()) throw new UserNotFoundException("User not found for " + userid);

        Optional<Order> orderOptional = orderRepo.findById(orderid);
        if(!orderOptional.isPresent()) throw new OrderNotFoundException("Order not found for " + orderid);

        return orderOptional.get();


    }
}
