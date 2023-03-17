package com.demo.project.controller;

import com.demo.project.model.AuthResponse;
import com.demo.project.model.UserModel;
import com.demo.project.model.dto.UserLogin;
import com.demo.project.repository.RoleRepository;
import com.demo.project.repository.UserRepository;
import com.demo.project.security.JWTGenerator;
import com.demo.project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserModel userModel) {
        return authService.register(userModel);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLogin userLogin){
        return authService.login(userLogin);
    }

}
