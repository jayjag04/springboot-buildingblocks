package com.stacksimplify.restservices.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value="/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {
    @Autowired
    private UserService userService;
    // getUserById
    @GetMapping("/{id}")
    public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {

        try {
            Optional<User> userOptional = userService.getUserById(id);
            User user = userOptional.get();

            Set<String> fields = new HashSet<String>();
            fields.add("userid");
             fields.add("ssn");
            fields.add("username");
            fields.add("orders");
            MappingJacksonValue mapper   = new MappingJacksonValue(user);
            FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept( fields ));
            mapper.setFilters(filters);
            return mapper;
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/params/{id}")
    public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id,
                                            @RequestParam Set<String> fields) { 

        try {
            Optional<User> userOptional = userService.getUserById(id);
            User user = userOptional.get();
            MappingJacksonValue mapper   = new MappingJacksonValue(user);
            FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter",
                    SimpleBeanPropertyFilter.filterOutAllExcept( fields ));
            mapper.setFilters(filters);
            return mapper;
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}
