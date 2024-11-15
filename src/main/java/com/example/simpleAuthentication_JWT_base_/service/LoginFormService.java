package com.example.simpleAuthentication_JWT_base_.service;

import com.example.simpleAuthentication_JWT_base_.model.*;
import com.example.simpleAuthentication_JWT_base_.repository.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

@Service
public class LoginFormService {
    private final LoginFormRepository loginFormRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginFormService(LoginFormRepository loginFormRepository, PasswordEncoder passwordEncoder) {
        this.loginFormRepository = loginFormRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public LoginForm userRegistry(LoginForm loginForm){
        LoginForm actualLoginForm = new LoginForm();
        actualLoginForm.setUsername(loginForm.getUsername());
        actualLoginForm.setPassword(passwordEncoder.encode(loginForm.getPassword()));
       return loginFormRepository.save(actualLoginForm);
    }
}
