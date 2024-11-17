package com.example.simpleAuthentication_JWT_base_.controller;

import com.example.simpleAuthentication_JWT_base_.model.*;
import com.example.simpleAuthentication_JWT_base_.service.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class LoginFormController {
    private final LoginFormService loginFormService;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;


    public LoginFormController(LoginFormService loginFormService, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.loginFormService = loginFormService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginForm loginForm){
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginForm.getUsername()
                        ,loginForm.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(loginForm.getUsername());
        }
        else {
            throw new UsernameNotFoundException("user not founded");

        }
    }

    @PostMapping("/adduser")
    public LoginForm addUser(@RequestBody LoginForm loginForm){
        return loginFormService.userRegistry(loginForm);
    }

    @GetMapping("/validate")
    public String validToken(@RequestParam("token")String token){
         jwtService.validateToken(token);
         return "token is valid";
    }
}
