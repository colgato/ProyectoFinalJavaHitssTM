package com.example.services;

import java.util.List;

import com.example.models.User;

public interface UserService {
    User save (User user);
    List<User> findAll();
    boolean existsByUsername(String username);
}
