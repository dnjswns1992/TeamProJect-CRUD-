package com.example.demo.controller;

import com.example.demo.entity.FormUserEntity;
import com.example.demo.repository.FormUserRepository;
import com.example.demo.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final FormUserRepository formUserRepository;
    private final JWTUtil jwtUtil;

    @GetMapping("/users")
    public List<FormUserEntity> allUsers() {
        return formUserRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Object oneUser(@PathVariable int id, HttpServletRequest request) {
        Optional<FormUserEntity> byId = formUserRepository.findById(id);

        String auth = request.getHeader(JWTUtil.AUTH_HEADER);

        if (auth == null || !auth.startsWith("Bearer") || byId.isEmpty()) {
            return "No";
        }

        String token = auth.split(" ")[1];
        String username = jwtUtil.getUsername(token);
        String username1 = byId.get().getUsername();

        if (!username1.equals(username)) {
            return "No tambien";
        }

        return byId.get();
    }
}
