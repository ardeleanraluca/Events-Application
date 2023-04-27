package com.demo.project.controller;

import com.demo.project.dto.EventDto;
import com.demo.project.service.EventServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles the HTTP requests related to events, translates the JSON parameter
 * to object, and authenticates the request and transfer it to the service layer.
 */

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventServiceInterface eventService;

    /**
     * Handles the api call for creating an event and transfer it to the service layer.
     */
    @PostMapping("/createEvent")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        EventDto createdEvent = eventService.createEvent(eventDto);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    /**
     * Handles the api call to update an event and transfer it to the service layer.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@RequestBody EventDto eventDto, @PathVariable Long id) {
        EventDto updatedEvent = eventService.updateEvent(eventDto, id);
        if (updatedEvent == null) {
            return new ResponseEntity<>(updatedEvent, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
        }

    }

    /**
     * Handles the api call for deleting an event and transfer it to the service layer.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        boolean deleted = eventService.deleteEvent(id);
        if (deleted) {
            return new ResponseEntity<>("Event deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(("Event deletion failed!"), HttpStatus.BAD_REQUEST);

        }
    }

    /**
     * Handles the api call for returning all events from a specified city and transfer it to the service layer.
     */
    @GetMapping("")
    public ResponseEntity<List<EventDto>> getEventsByCity(@RequestParam("city") String city) {
        List<EventDto> events = eventService.getEventsByCity(city);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

}
