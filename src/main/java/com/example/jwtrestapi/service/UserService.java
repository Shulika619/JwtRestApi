package com.example.jwtrestapi.service;

import com.example.jwtrestapi.model.User;

import java.util.List;

public interface UserService {
    User register(User user);
    List<User> getAll();
    User findById(Long id);
    User findByUsername(String username);
    void deleted(Long id);
}
