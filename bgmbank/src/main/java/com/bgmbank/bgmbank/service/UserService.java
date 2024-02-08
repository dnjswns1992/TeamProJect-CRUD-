package com.bgmbank.bgmbank.service;

import com.bgmbank.bgmbank.dto.user.UserRegisterRequest;
import com.bgmbank.bgmbank.entity.user.User;
import com.bgmbank.bgmbank.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final String DEFAULT_USER = "ROLE_USER";
    private final String DEFAULT_TIER = "bronze";
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<String> registerUser(UserRegisterRequest userRegisterRequest) {

        if (isEmailAlreadyExist(userRegisterRequest.getEmail())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This Email is Already used");
        }

        if (isNicknameAlreadyExist(userRegisterRequest.getNickname())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This Nickname is Already used");
        }

        User mappedUser = modelMapper.map(userRegisterRequest, User.class);

        String encryptedPwd = passwordEncoder.encode(mappedUser.getPwd());
        mappedUser.setPwd(encryptedPwd);
        mappedUser.setRole(DEFAULT_USER);
        mappedUser.setTier(DEFAULT_TIER);
        mappedUser.setPoint(0L);

        userRepository.save(mappedUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("Registration Completed");
    }

    public boolean isEmailAlreadyExist(String email) {

        User byEmail = userRepository.findByEmail(email);
        return byEmail != null;
    }

    public boolean isNicknameAlreadyExist(String nickname) {
        User byNickname = userRepository.findByNickname(nickname);
        return byNickname != null;
    }
}
