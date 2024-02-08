package com.bgmbank.bgmbank.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

    private String nickname;
    private String email;
    private String pwd;
    private String profileImgUrl;
}
