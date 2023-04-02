package com.demo.project.controller;

import com.demo.project.dto.EventDto;
import com.demo.project.entity.EventEntity;
import com.demo.project.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@RequestBody EventDto eventDto, @PathVariable Long id) {
        return eventService.updateEvent(eventDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent( @PathVariable Long id) {
        return eventService.deleteEvent(id);
    }

    @GetMapping("")
    public ResponseEntity<List<EventEntity>> getEventsByCity(@RequestParam("city")String city) {
        return eventService.getEventsByCity(city);
    }

}
