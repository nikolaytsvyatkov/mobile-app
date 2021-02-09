package com.example.mobileapp.service;


import com.example.mobileapp.model.User;

public interface AuthService {
    User register(User user);
    User login(String username, String password);
}
