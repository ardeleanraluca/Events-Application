package com.demo.project.controller;

import com.demo.project.entity.StandardUserEntity;
import com.demo.project.dto.StandardUserModel;
import com.demo.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody StandardUserModel newUser, @PathVariable Long id) {
        return userService.update(newUser, id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StandardUserEntity>> getAllStandardUsers() {
        return userService.getAllStandardUsers();
    }

}
