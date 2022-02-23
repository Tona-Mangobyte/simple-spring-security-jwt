package com.st.simple.services.impl;

import com.st.simple.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        com.st.simple.entities.User user = userService.findByUserName(username);
        System.out.println(user);
        return new User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}
