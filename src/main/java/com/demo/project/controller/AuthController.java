package com.demo.project.controller;

import com.demo.project.dto.AuthResponse;
import com.demo.project.dto.StandardUserModel;
import com.demo.project.dto.UserLogin;
import com.demo.project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody StandardUserModel standardUserModel) {
        return authService.register(standardUserModel);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLogin userLogin){
        return authService.login(userLogin);
    }

}
