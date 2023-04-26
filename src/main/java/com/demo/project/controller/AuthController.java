package com.demo.project.controller;

import com.demo.project.dto.*;
import com.demo.project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles the HTTP requests related to authentication and registration, translates the JSON parameter
 * to object, and authenticates the request and transfer it to the service layer.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * Handles the api call for registration of a new user and transfer it to the service layer.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody StandardUserDto standardUserDto) {

        return authService.register(standardUserDto);
    }

    /**
     * Handles the api call for registration of a new organizer and transfer it to the service layer
     * if the user which try to make this request is an admin
     * else it return a bad request
     */
    @PostMapping("/registerOrganizer")
    public ResponseEntity<String> registerOrganizer(@RequestBody OrganizerDto organizerDto) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN")))
            return authService.registerOrganizer(organizerDto);
//        return new ResponseEntity<>("Organizer can not be registered because you are not an admin!", HttpStatus.FORBIDDEN);
    }


    /**
     * Handles the api call for authentication of a user and transfer it to the service layer.
     */
    @PostMapping("/login")
    public ResponseEntity<UserAccountDto> login(@RequestBody UserLoginDto userLoginDto) {
        return authService.login(userLoginDto);
    }

}
