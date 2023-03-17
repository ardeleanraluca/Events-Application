package com.demo.project.service;

import com.demo.project.model.UserAccModel;
import com.demo.project.entity.UserAccEntity;
import com.demo.project.repository.RoleRepository;
import com.demo.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> delete(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>("User doesn't exists", HttpStatus.BAD_REQUEST);
        }
        ;

        userRepository.deleteById(id);
        return new ResponseEntity<>("User deleted success!", HttpStatus.OK);
    }

    public ResponseEntity<String> update(UserAccModel newUser, Long id) {
        if (userRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>("User doesn't exists", HttpStatus.BAD_REQUEST);
        }

        UserAccEntity userEntity = userRepository.findById(id).get();
        userEntity.setName(newUser.getFirstName() + " " + newUser.getLastName());
        userEntity.setPassword(passwordEncoder.encode((newUser.getPassword())));
        userRepository.saveAndFlush(userEntity);

        return new ResponseEntity<>("User updated success!", HttpStatus.OK);
    }

    public ResponseEntity<List<UserAccEntity>> getAllUsers() {
        if (roleRepository.findByName("USER").isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<List<UserAccEntity>> entities = userRepository.findAllByRole(roleRepository.findByName("USER").get());
        if (entities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<UserAccEntity> users = entities.get();


        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
