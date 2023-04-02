package com.demo.project.controller;

import com.demo.project.dto.LocationDto;
import com.demo.project.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private LocationService locationService;

    /**
     * Handles the api call for creating a location for an event and transfer it to the service layer.
     */
    @PostMapping("/createLocation")
    public ResponseEntity<String> createLocation(@RequestBody LocationDto locationDto) {
        return locationService.createLocation(locationDto);
    }

    /**
     * Handles the api call for deleting a location and transfer it to the service layer.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id) {
        return locationService.deleteLocation(id);
    }
}
