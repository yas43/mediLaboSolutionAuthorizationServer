package com.example.simpleAuthentication_JWT_base_.service;

import com.example.simpleAuthentication_JWT_base_.model.*;
import com.example.simpleAuthentication_JWT_base_.repository.*;
import org.springframework.stereotype.*;

@Service
public class LoginFormService {
    private final LoginFormRepository loginFormRepository;

    public LoginFormService(LoginFormRepository loginFormRepository) {
        this.loginFormRepository = loginFormRepository;
    }
    public LoginForm userRegistry(LoginForm loginForm){
        LoginForm actualLoginForm = new LoginForm();
        actualLoginForm.setUsername(loginForm.getUsername());
        actualLoginForm.setPassword(loginForm.getPassword());
       return loginFormRepository.save(actualLoginForm);
    }
}
