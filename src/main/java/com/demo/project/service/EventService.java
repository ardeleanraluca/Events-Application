package com.demo.project.service;

import com.demo.project.dto.EventDto;
import com.demo.project.dto.TicketDto;
import com.demo.project.entity.*;
import com.demo.project.repository.EventRepository;
import com.demo.project.repository.LocationRepository;
import com.demo.project.repository.OrganizerRepository;
import com.demo.project.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    public ResponseEntity<String> createEvent(EventDto eventDto) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setName(eventDto.getName());
        eventEntity.setDescription(eventDto.getDescription());
        eventEntity.setCategory(eventDto.getCategory());
        eventEntity.setDate(eventDto.getDate());
        eventEntity.setHour(eventDto.getHour());

        //// TO DO DELETE va fi luat din security context
        OrganizerEntity organizerEntity = organizerRepository.findById(eventDto.getOrganizerId()).get();
        eventEntity.setOrganizer(organizerEntity);

        //// TO DO DELETE va fi un dropDown si daca nu se gaseste va fi un link de a crea sala noua
        LocationEntity locationEntity = locationRepository.findById(eventDto.getLocationId()).get();
        eventEntity.setLocation(locationEntity);
        List<TicketDto> ticketDtos = eventDto.getTickets();
        System.out.println(ticketDtos.size());
        List<TicketEntity> ticketEntities = new ArrayList<>();

        for (TicketDto ticketDto : ticketDtos) {
            TicketEntity ticketEntity = new TicketEntity();
            ticketEntity.setCategory(ticketDto.getCategory());
            ticketEntity.setDiscount(ticketDto.getDiscount());
            ticketEntity.setPrice(ticketDto.getPrice());
            ticketEntity.setEvent(eventEntity);
            ticketEntities.add(ticketEntity);
//            ticketRepository.saveAndFlush(ticketEntity); -- se salveaza cand se salveaza evenimentul
        }
        eventEntity.setTickets(ticketEntities);
        eventRepository.saveAndFlush(eventEntity);
        return new ResponseEntity<>("Event created successfully!", HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<String> updateEvent(EventDto eventDto, Long id) {
        if (eventRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>("Event doesn't exists", HttpStatus.BAD_REQUEST);
        }
        EventEntity eventEntity = eventRepository.findById(id).get();
        eventEntity.setName(eventDto.getName());
        eventEntity.setDescription(eventDto.getDescription());
        eventEntity.setCategory(eventDto.getCategory());
        eventEntity.setDate(eventDto.getDate());
        eventEntity.setHour(eventDto.getHour());


        //// TO DO DELETE va fi un dropDown si daca nu se gaseste va fi un link de a crea sala noua
        LocationEntity locationEntity = locationRepository.findById(eventDto.getLocationId()).get();
        eventEntity.setLocation(locationEntity);

        List<TicketDto> ticketDtos = eventDto.getTickets();
        List<TicketEntity> ticketEntities = new ArrayList<>();
        ticketRepository.deleteAllByEvent(eventEntity);

        for (TicketDto ticketDto : ticketDtos) {
            TicketEntity ticketEntity = new TicketEntity();
            ticketEntity.setCategory(ticketDto.getCategory());
            ticketEntity.setDiscount(ticketDto.getDiscount());
            ticketEntity.setPrice(ticketDto.getPrice());
            ticketEntity.setEvent(eventEntity);
            ticketEntities.add(ticketEntity);
        }
        eventEntity.setTickets(ticketEntities);
        eventRepository.saveAndFlush(eventEntity);
        return new ResponseEntity<>("Event updated successfully!", HttpStatus.OK);

    }

    public ResponseEntity<String> deleteEvent(Long id) {
        if (eventRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>("Event doesn't exists", HttpStatus.BAD_REQUEST);
        }

        eventRepository.deleteById(id);
        return new ResponseEntity<>("Event deleted successfully!", HttpStatus.OK);
    }

    public ResponseEntity<List<EventEntity>> getEventsByCity(String city) {
        List<EventEntity> eventEntities = eventRepository.findAllByLocation_City(city);
        return new ResponseEntity<>(eventEntities, HttpStatus.OK);
    }


}
