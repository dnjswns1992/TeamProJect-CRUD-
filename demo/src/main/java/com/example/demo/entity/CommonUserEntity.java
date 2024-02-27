package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CommonUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commonUserId;
    private String username;
    private String provider;
    private String nickname;
    private String role;
    private String userIp;

    @OneToOne
    @JoinColumn(name = "form_user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private FormUserEntity formUserEntity;

    @OneToOne
    @JoinColumn(name = "oauth2_user_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Oauth2UserEntity oauth2UserEntity;

    @OneToMany(mappedBy = "commonUserEntity", cascade = CascadeType.ALL)
    private List<PostEntity> posts = new ArrayList<>();
}
