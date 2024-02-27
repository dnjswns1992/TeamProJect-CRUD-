package com.example.demo.service;

import com.example.demo.dto.Form2CommonUserDTO;
import com.example.demo.dto.FormUserDTO;
import com.example.demo.entity.CommonUserEntity;
import com.example.demo.entity.FormUserEntity;
import com.example.demo.repository.CommonUserRepository;
import com.example.demo.repository.FormUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpService {

    private static final String BASIC_ROLE = "ROLE_USER";
    private static final String FORM_PROVIDER = "FormLogin";

    private final FormUserRepository formUserRepository;
    private final CommonUserRepository commonUserRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> SignUp(FormUserDTO formUserDTO, HttpServletRequest request) {

        String email = formUserDTO.getEmail();
        String nickname = formUserDTO.getNickname();
        String username = formUserDTO.getUsername();

        if (formUserRepository.existsByEmailAndUsername(email, username) || formUserRepository.existsByNickname(nickname)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 계정입니다.");
        }

        if (nickname == null || nickname.isBlank()) {
            Random random = new Random();
            formUserDTO.setNickname("Anonymous" + random.nextInt(100000));
        }
        if (formUserDTO.getProvider() == null) {
            formUserDTO.setProvider(FORM_PROVIDER);
        }
        String encryptedPwd = passwordEncoder.encode(formUserDTO.getPassword());
        formUserDTO.setPassword(encryptedPwd);
        formUserDTO.setRole(BASIC_ROLE);
        formUserDTO.setUserIP(request.getRemoteAddr());

        FormUserEntity formUser = modelMapper.map(formUserDTO, FormUserEntity.class);
        formUserRepository.save(formUser);

        CommonUserEntity commonUser = modelMapper.map(changeFormUser2CommonUser(formUser),
                CommonUserEntity.class);
        commonUserRepository.save(commonUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입에 성공했습니다.");
    }

    private Form2CommonUserDTO changeFormUser2CommonUser(FormUserEntity formUser) {

        return Form2CommonUserDTO.builder()
                .username(formUser.getUsername())
                .provider(formUser.getProvider())
                .nickname(formUser.getNickname())
                .role(formUser.getRole())
                .userIp(formUser.getUserIP())
                .formUserEntity(formUser)
                .build();
    }
}
