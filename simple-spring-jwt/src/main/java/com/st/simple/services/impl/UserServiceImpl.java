package com.st.simple.services.impl;

import com.st.simple.entities.User;
import com.st.simple.repositories.UserRepo;
import com.st.simple.services.UserService;
import com.st.simple.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User createUser(UserValidator validator) {
        User user = new User();
        user.setUsername(validator.getUsername());
        user.setPassword(passwordEncoder.encode(validator.getPassword()));
        user.setEnable(true);
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        return userRepo.save(user);
    }

    @Override
    public User findByUserName(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }
}
