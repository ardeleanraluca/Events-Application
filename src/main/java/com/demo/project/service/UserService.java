package com.demo.project.service;

import com.demo.project.model.UserModel;
import com.demo.project.entity.UserEntity;
import com.demo.project.model.dto.UserLogin;
import com.demo.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

}
