package com.st.simple.controllers;

import com.st.simple.entities.User;
import com.st.simple.services.UserService;
import com.st.simple.validators.UserValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> index() {
        return userService.findAll();
    }

    @GetMapping("/username")
    public User getByUserName(@RequestParam("username") String username) {
        return userService.findByUserName(username);
    }

    @PostMapping("/register")
    public User createAccount(@RequestBody UserValidator validator) {
        System.out.println(validator);
        return userService.createUser(validator);
    }
}
