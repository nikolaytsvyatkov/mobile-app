package com.example.mobileapp.service.impl;


import com.example.mobileapp.exception.InvalidEntityException;
import com.example.mobileapp.model.enums.Role;
import com.example.mobileapp.model.User;
import com.example.mobileapp.service.AuthService;
import com.example.mobileapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;

    @Override
    public User register(User user) {
        if(user.getRoles().contains(Role.ADMIN) ) {
            throw new InvalidEntityException("Admins can not self-register.");
        }
        return userService.createUser(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return null;
        }

        if(user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

}
