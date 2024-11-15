package com.example.simpleAuthentication_JWT_base_.service;

import com.example.simpleAuthentication_JWT_base_.model.*;
import com.example.simpleAuthentication_JWT_base_.repository.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final LoginFormRepository loginFormRepository;

    public CustomUserDetailService(LoginFormRepository loginFormRepository) {
        this.loginFormRepository = loginFormRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginForm loginForm = loginFormRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("username not founded"));

        UserDetails userDetails = User.builder()
                .username(loginForm.getUsername())
                .password(loginForm.getPassword())
                .build();
        return userDetails;
    }
}
