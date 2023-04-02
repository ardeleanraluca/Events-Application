package com.demo.project.controller;

import com.demo.project.dto.EventDto;
import com.demo.project.entity.EventEntity;
import com.demo.project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private EventService eventService;

    /**
     * Handles the api call for creating an event and transfer it to the service layer.
     */
    @PostMapping("/createEvent")
    public ResponseEntity<String> createEvent(@RequestBody EventDto eventDto) {
        return eventService.createEvent(eventDto);
    }

    /**
     * Handles the api call to update an event and transfer it to the service layer.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@RequestBody EventDto eventDto, @PathVariable Long id) {
        return eventService.updateEvent(eventDto, id);
    }

    /**
     * Handles the api call for deleting an event and transfer it to the service layer.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent( @PathVariable Long id) {
        return eventService.deleteEvent(id);
    }

    /**
     * Handles the api call for returning all events from a specified city and transfer it to the service layer.
     */
    @GetMapping("")
    public ResponseEntity<List<EventEntity>> getEventsByCity(@RequestParam("city")String city) {
        return eventService.getEventsByCity(city);
    }

}
