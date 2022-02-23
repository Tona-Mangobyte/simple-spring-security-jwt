package com.st.simple.services;

import com.st.simple.entities.User;
import com.st.simple.validators.UserValidator;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User createUser(UserValidator validator);
    User findByUserName(String username);
}
