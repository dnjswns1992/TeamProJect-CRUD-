package com.example.demo.dto;

import com.example.demo.entity.FormUserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Form2CommonUserDTO {

    private String username;
    private String provider;
    private String nickname;
    private String role;
    private String userIp;
    private FormUserEntity formUserEntity;

    @Builder
    public Form2CommonUserDTO(String username, String provider, String nickname, String role, String userIp, FormUserEntity formUserEntity) {
        this.username = username;
        this.provider = provider;
        this.nickname = nickname;
        this.role = role;
        this.userIp = userIp;
        this.formUserEntity = formUserEntity;
    }
}
