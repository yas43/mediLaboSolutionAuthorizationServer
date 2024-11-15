package com.example.simpleAuthentication_JWT_base_.controller;

import com.example.simpleAuthentication_JWT_base_.model.*;
import com.example.simpleAuthentication_JWT_base_.service.*;
import org.springframework.security.crypto.password.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class LoginFormController {
    private final LoginFormService loginFormService;


    public LoginFormController(LoginFormService loginFormService) {
        this.loginFormService = loginFormService;
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("username","yaser");
        return "home";
    }

    @PostMapping("/adduser")
    public LoginForm addUser(@RequestBody LoginForm loginForm){
        return loginFormService.userRegistry(loginForm);
    }
}
