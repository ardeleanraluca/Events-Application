package com.demo.project.controller;

import com.demo.project.dto.LocationDto;
import com.demo.project.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/createLocation")
    public ResponseEntity<String> createLocation(@RequestBody LocationDto locationDto) {
        return locationService.createLocation(locationDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id) {
        return locationService.deleteLocation(id);
    }
}
