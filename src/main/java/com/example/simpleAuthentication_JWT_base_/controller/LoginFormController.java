package com.example.simpleAuthentication_JWT_base_.controller;

import com.example.simpleAuthentication_JWT_base_.model.*;
import com.example.simpleAuthentication_JWT_base_.service.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
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
    public ResponseEntity<?> authenticate(@RequestBody LoginForm loginForm, HttpServletResponse response){
        System.out.println("we are in authorization server ");
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginForm.getUsername()
                        ,loginForm.getPassword()));
        if (authentication.isAuthenticated()){
            System.out.println("we are in authorization server user authenticated");
            String token = jwtService.generateToken(authentication);
            ResponseEntity<String> response1 = ResponseEntity.ok(token);
            System.out.println(" generated token is "+token);
            return response1;
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credential");

    }

    @PostMapping("/adduser")
    public LoginForm addUser(@RequestBody LoginForm loginForm){
        return loginFormService.userRegistry(loginForm);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validToken(@RequestParam("token")String token){
        try {
            jwtService.validateToken(token);
            return ResponseEntity.ok("Valid");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid");
        }
    }

    @GetMapping("/findUsername")
    public LoginForm findLoginForm(@RequestParam("username")String username){
        return loginFormService.findLoginForm(username);
    }

    @GetMapping("/getUsernameByToken")
    public ResponseEntity<String> getUsernameByToken(@RequestParam("token")String token){
        try {
            String username = jwtService.getUsername(token);
            return ResponseEntity.ok(username);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid token"+e.getMessage());
        }

    }
}
