package com.demo.project.service;

import com.demo.project.dto.OrganizerDto;
import com.demo.project.dto.StandardUserDto;
import com.demo.project.entity.OrganizerEntity;
import com.demo.project.entity.StandardUserEntity;
import com.demo.project.entity.UserAccountEntity;
import com.demo.project.repository.OrganizerRepository;
import com.demo.project.repository.RoleRepository;
import com.demo.project.repository.StandardUserRepository;
import com.demo.project.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class handles all requests related to users, whatever if they are standard users or organizers.
 */
@Component
public class UserService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private StandardUserRepository standardUserRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OrganizerRepository organizerRepository;

    /**
     * Deletes a user from the database, if it exists.
     * @param id
     * @return ResponseEntity - OK if the user was deleted successfully, otherwise BAD_REQUEST.
     */
    public ResponseEntity<String> delete(Long id) {
        if (standardUserRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>("User doesn't exists", HttpStatus.BAD_REQUEST);
        }

        standardUserRepository.deleteById(id);
        return new ResponseEntity<>("User deleted success!", HttpStatus.OK);
    }

    /**
     * Deletes an organizer with his events from the database, if it exists.
     * @param id
     * @return ResponseEntity - OK if the organizer was deleted successfully, otherwise BAD_REQUEST.
     */
    public ResponseEntity<String> deleteOrganizer(Long id) {
        if (organizerRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>("Organizer doesn't exists", HttpStatus.BAD_REQUEST);
        }

        organizerRepository.deleteById(id);
        return new ResponseEntity<>("Organizer and his events deleted successfully!", HttpStatus.OK);
    }

    /**
     * Updates a user's data in database, if it exists.
     * @param newUser an object that contains all the details of a standard user that will be updated.
     * @param id The user ID that is being updated.
     * @return ResponseEntity - OK if the user was updated successfully, otherwise BAD_REQUEST.
     */
    public ResponseEntity<String> update(StandardUserDto newUser, Long id) {
        if (userAccountRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>("User doesn't exists", HttpStatus.BAD_REQUEST);
        }

        UserAccountEntity userEntity = userAccountRepository.findById(id).get();
        StandardUserEntity standardUserEntity = standardUserRepository.findByUserAccountEntity(userEntity);

        userEntity.setName(newUser.getFirstName() + " " + newUser.getLastName());
        userEntity.setPassword(passwordEncoder.encode((newUser.getPassword())));
        userAccountRepository.saveAndFlush(userEntity);

        standardUserEntity.setUserAccountEntity(userEntity);
        standardUserEntity.setCounty(newUser.getCounty());
        standardUserEntity.setCity(newUser.getCity());
        standardUserEntity.setDateOfBirth(newUser.getDateOfBirth());
        standardUserRepository.saveAndFlush(standardUserEntity);

        return new ResponseEntity<>("User updated success!", HttpStatus.OK);
    }

    /**
     * Updates an organizer's data in database, if it exists.
     * @param newOrganizer an object that contains all the details of a standard user that will be updated.
     * @param id The user ID that is being updated.
     * @return ResponseEntity - OK if the user was updated successfully, otherwise BAD_REQUEST.
     */
    public ResponseEntity<String> updateOrganizer(OrganizerDto newOrganizer, Long id) {
        if (userAccountRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>("Organizer doesn't exists", HttpStatus.BAD_REQUEST);
        }

        UserAccountEntity userEntity = userAccountRepository.findById(id).get();
        OrganizerEntity organizerEntity = organizerRepository.findByUserAccountEntity(userEntity);

        userEntity.setName(newOrganizer.getFirstName() + " " + newOrganizer.getLastName());
        userEntity.setPassword(passwordEncoder.encode((newOrganizer.getPassword())));
        userAccountRepository.saveAndFlush(userEntity);

        organizerEntity.setUserAccountEntity(userEntity);
        organizerRepository.saveAndFlush(organizerEntity);


        return new ResponseEntity<>("Organizer updated successfully!", HttpStatus.OK);
    }

    /**
     * Finds all standard user in database and returns them
     * @return all standard users from database
     */
    public ResponseEntity<List<StandardUserEntity>> getAllStandardUsers() {
        if (roleRepository.findByName("USER").isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<StandardUserEntity> users = standardUserRepository.findAll();


        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
