package com.bgmbank.bgmbank.controller;

import com.bgmbank.bgmbank.dto.user.UserRegisterRequest;
import com.bgmbank.bgmbank.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {

        return userService.registerUser(userRegisterRequest);
    }
}
