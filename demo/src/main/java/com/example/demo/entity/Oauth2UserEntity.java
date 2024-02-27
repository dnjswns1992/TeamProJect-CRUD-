package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity(name = "oauth2_user_entity")
public class Oauth2UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oauth2UserId;
    private String role;
    private String provider;
    private String email;
    private LocalDate createAt;
    private String userIP;
}
