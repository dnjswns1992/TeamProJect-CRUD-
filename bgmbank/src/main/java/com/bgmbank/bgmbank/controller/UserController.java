package com.bgmbank.bgmbank.controller;

import com.bgmbank.bgmbank.entity.user.User;
import com.bgmbank.bgmbank.repository.user.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/members")
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
