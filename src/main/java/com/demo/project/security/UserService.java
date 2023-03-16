package com.demo.project.security;

import com.demo.project.model.UserModel;
import com.demo.project.entity.UserEntity;
import com.demo.project.repository.UserRepository;
import com.demo.project.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ResponseEntity<String> register(UserModel userModel) {
        if (userRepository.existsByEmail(userModel.getEmail())) {
            return new ResponseEntity<>("An account is already registered with this email!", HttpStatus.BAD_REQUEST);
        }


        UserEntity user = new UserEntity();
        user.setUserType(UserType.Standard_User);
        user.setName(userModel.getFirstName() + " " + userModel.getLastName());
        user.setDateOfBirth(userModel.getDateOfBirth());
        user.setCity(userModel.getCity());
        user.setCounty(userModel.getCounty());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
        userRepository.saveAndFlush(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}
