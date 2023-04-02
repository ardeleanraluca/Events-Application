package com.demo.project.controller;

import com.demo.project.entity.StandardUserEntity;
import com.demo.project.dto.StandardUserDto;
import com.demo.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Handles the HTTP requests related to users, translates the JSON parameter
 * to object, and authenticates the request and transfer it to the service layer.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Handles the api call for deleting of a user and transfer it to the service layer.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    /**
     * Handles the api call for deleting of an organizer with his events and transfer it to the service layer.
     */
    @DeleteMapping("/deleteOrganizer/{id}")
    public ResponseEntity<String> deleteOrganizer(@PathVariable Long id) {
        return userService.deleteOrganizer(id);
    }


    /**
     * Handles the api call to update a user's data and transfer it to the service layer.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody StandardUserDto newUser, @PathVariable Long id) {
        return userService.update(newUser, id);
    }

    /**
     * Handles the api call for returning all standard users and transfer it to the service layer.
     */
    @GetMapping("/all")
    public ResponseEntity<List<StandardUserEntity>> getAllStandardUsers() {
        return userService.getAllStandardUsers();
    }

}
