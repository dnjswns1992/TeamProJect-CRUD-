package com.example.demo.controller;

import com.example.demo.dto.FormUserDTO;
import com.example.demo.service.SignUpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("/sign_up")
    public ResponseEntity<String> SignUp(@RequestBody FormUserDTO formUserDTODTO, HttpServletRequest request) {
        return signUpService.SignUp(formUserDTODTO, request);
    }
}
