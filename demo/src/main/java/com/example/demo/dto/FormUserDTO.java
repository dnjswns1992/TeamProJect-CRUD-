package com.example.demo.dto;

import lombok.Data;

@Data
public class FormUserDTO {

    private String nickname;
    private String username;
    private String password;
    private String role;
    private String provider;
    private String email;
    private String userIP;
}
