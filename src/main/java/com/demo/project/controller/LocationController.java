package com.demo.project.controller;

import com.demo.project.dto.LocationDto;
import com.demo.project.service.LocationSeviceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Handles the HTTP requests related to events'location, translates the JSON parameter
 * to object, and authenticates the request and transfer it to the service layer.
 */

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationSeviceInterface locationService;

    /**
     * Handles the api call for creating a location for an event and transfer it to the service layer.
     */
    @PostMapping("/createLocation")
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto) {
        LocationDto createdLocation = locationService.createLocation(locationDto);
        if(createdLocation==null){
            return new ResponseEntity<>(createdLocation, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(createdLocation, HttpStatus.OK);
        }
    }

    /**
     * Handles the api call for deleting a location and transfer it to the service layer.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id) {
        boolean deleted =  locationService.deleteLocation(id);
        if (deleted) {
            return new ResponseEntity<>("Location deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(("Location deletion failed - Location do not exists or exists events that take place here! "), HttpStatus.BAD_REQUEST);

        }
    }
}
