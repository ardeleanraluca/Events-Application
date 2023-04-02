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

@Component
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private EventRepository eventRepository;

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

    /// can not delete
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
