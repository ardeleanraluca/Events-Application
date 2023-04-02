package com.demo.project.service;

import com.demo.project.dto.LocationDto;
import com.demo.project.entity.LocationEntity;
import com.demo.project.repository.EventRepository;
import com.demo.project.repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * This class handles all requests related to events'location.
 */
@Component
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private EventRepository eventRepository;

    /**
     * Create a location for an event and adds it into database
     *
     * @param locationDto an object that contains all the details of an location
     * @return ResponseEntity - If the location was successfully added in the database it return 200 OK, otherwise
     * the registration will not succeed.
     */
    @Transactional
    public ResponseEntity<String> createLocation(LocationDto locationDto) {
        if (locationRepository.findByNameAndCountyAndCity(
                locationDto.getName(), locationDto.getCounty(), locationDto.getCity()) != null) {
            return new ResponseEntity<>("Location with this name in this city already exists!", HttpStatus.CREATED);
        }
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setAddress(locationDto.getAddress());
        locationEntity.setNoSeats(locationDto.getNoSeats());
        locationEntity.setName(locationDto.getName());
        locationEntity.setCity(locationDto.getCity());
        locationEntity.setCounty(locationDto.getCounty());
        locationRepository.saveAndFlush(locationEntity);
        return new ResponseEntity<>("Location created successfully!", HttpStatus.OK);
    }

    /**
     * Deletes a location from the database, if it exists.
     *
     * @param id the location ID that is being deleted.
     * @return ResponseEntity - OK if the event was deleted successfully, otherwise BAD_REQUEST.
     */
    @Transactional
    public ResponseEntity<String> deleteLocation(Long id) {
        if (locationRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>("Location do not exists!", HttpStatus.BAD_REQUEST);
        }

        if (eventRepository.existsByLocation_Id(id)) {
            return new ResponseEntity<>("You can not delete this location because exists events that take place here!", HttpStatus.OK);
        } else {
            locationRepository.deleteById(id);
        }
        return new ResponseEntity<>("Location deleted successfully!", HttpStatus.OK);
    }
}
