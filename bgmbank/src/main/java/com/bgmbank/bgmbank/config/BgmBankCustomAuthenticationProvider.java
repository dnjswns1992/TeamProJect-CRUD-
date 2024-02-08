package com.bgmbank.bgmbank.config;

import com.bgmbank.bgmbank.entity.user.User;
import com.bgmbank.bgmbank.repository.user.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BgmBankCustomAuthenticationProvider implements AuthenticationProvider {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public BgmBankCustomAuthenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        User user = userRepository.findByEmail(email);

        if (user != null) {
            if (passwordEncoder.matches(pwd, user.getPwd())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRole()));

                return new UsernamePasswordAuthenticationToken(email, pwd, authorities);
            } else {
                throw new BadCredentialsException("Invalid Email or Password");
            }
        }else {
            throw new BadCredentialsException("Invalid Email or Password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
