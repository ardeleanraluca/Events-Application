package com.demo.project.service;

import com.demo.project.entity.RoleEntity;
import com.demo.project.entity.UserAccEntity;
import com.demo.project.model.AuthResponse;
import com.demo.project.model.UserAccModel;
import com.demo.project.model.dto.UserLogin;
import com.demo.project.repository.RoleRepository;
import com.demo.project.repository.UserRepository;
import com.demo.project.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTGenerator jwtGenerator;


    public ResponseEntity<String> register(UserAccModel userModel) {
        if (userRepository.existsByEmail(userModel.getEmail())) {
            return new ResponseEntity<>("An account is already registered with this email!", HttpStatus.BAD_REQUEST);
        }

        UserAccEntity user = new UserAccEntity();
        user.setName(userModel.getFirstName() + " " + userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode((userModel.getPassword())));
        RoleEntity role = roleRepository.findByName("USER").get();
        user.setRole(role);


        userRepository.saveAndFlush(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }


    public ResponseEntity<AuthResponse> login(UserLogin userLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getEmail(),
                        userLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }
}
