package com.example.demo.service.security;

import com.example.demo.entity.FormUserEntity;
import com.example.demo.repository.FormUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final FormUserRepository formUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<FormUserEntity> byUsername = formUserRepository.findByEmail(username);

        if (byUsername.isPresent()) {
            return new CustomUserDetails(byUsername.get());
        }

        return null;
    }
}
