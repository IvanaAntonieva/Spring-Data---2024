package com.example.demo.data.service;

import com.example.demo.data.entities.User;

public interface UserService {
    void register (User user);

    User findUserById(int id);

    User findByName(String name);
}
