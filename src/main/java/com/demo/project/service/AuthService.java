package com.demo.project.service;

import com.demo.project.entity.RoleEntity;
import com.demo.project.entity.UserAccountEntity;
import com.demo.project.entity.StandardUserEntity;
import com.demo.project.dto.AuthResponse;
import com.demo.project.dto.StandardUserModel;
import com.demo.project.dto.UserLogin;
import com.demo.project.repository.RoleRepository;
import com.demo.project.repository.UserAccountRepository;
import com.demo.project.repository.StandardUserRepository;
import com.demo.project.security.JWTGenerator;
import jakarta.transaction.Transactional;
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
    private UserAccountRepository userAccountRepository;

    @Autowired
    private StandardUserRepository standardUserRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTGenerator jwtGenerator;


    @Transactional
    public ResponseEntity<String> register(StandardUserModel standardUserModel) {
        if (userAccountRepository.existsByEmail(standardUserModel.getEmail())) {
            return new ResponseEntity<>("An account is already registered with this email!", HttpStatus.BAD_REQUEST);
        }

        UserAccountEntity userAcc = new UserAccountEntity();
        userAcc.setName(standardUserModel.getFirstName() + " " + standardUserModel.getLastName());
        userAcc.setEmail(standardUserModel.getEmail());
        userAcc.setPassword(passwordEncoder.encode((standardUserModel.getPassword())));
        RoleEntity role = roleRepository.findByName("USER").get();
        userAcc.setRole(role);
        userAccountRepository.saveAndFlush(userAcc);

        StandardUserEntity user = new StandardUserEntity();
        user.setUserAccountEntity(userAcc);
        user.setDateOfBirth(standardUserModel.getDateOfBirth());
        user.setCounty(standardUserModel.getCounty());
        user.setCity(standardUserModel.getCity());
        standardUserRepository.saveAndFlush(user);

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
