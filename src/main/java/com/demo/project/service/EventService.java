package com.demo.project.service;

import com.demo.project.dto.EventDto;
import com.demo.project.dto.TicketDto;
import com.demo.project.entity.EventEntity;
import com.demo.project.entity.LocationEntity;
import com.demo.project.entity.OrganizerEntity;
import com.demo.project.entity.TicketEntity;
import com.demo.project.repository.EventRepository;
import com.demo.project.repository.LocationRepository;
import com.demo.project.repository.OrganizerRepository;
import com.demo.project.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles all requests related to events.
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
public class EventService implements EventServiceInterface {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private TicketRepository ticketRepository;

    /**
     * Create an event and adds it into database
     *
     * @param eventDto an object that contains all the details of an event
     * @return EventDto - If the event was successfully added in the database it returns created event, otherwise
     * the registration will not succeed and it returns null.
     */
    @Transactional
    public EventDto createEvent(EventDto eventDto) {
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
            ticketEntity.setDiscount(ticketDto.getDiscount());
            ticketEntity.setPrice(ticketDto.getPrice());
            ticketEntity.setEvent(eventEntity);
            ticketEntities.add(ticketEntity);
//            ticketRepository.saveAndFlush(ticketEntity); -- se salveaza cand se salveaza evenimentul
        }
        eventEntity.setTickets(ticketEntities);
        eventRepository.saveAndFlush(eventEntity);
        return new EventDto(eventEntity);

    }

    /**
     * Update an event's data in database, if it exists.
     *
     * @param eventDto an object that contains all the details of an event that will be updated.
     * @param id       The event ID that is being updated.
     * @return EventDto - the updated event if the event was updated successfully, otherwise null.
     */
    @Transactional
    public EventDto updateEvent(EventDto eventDto, Long id) {
        if (eventRepository.findById(id).isEmpty()) {
            return null;
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
            ticketEntity.setDiscount(ticketDto.getDiscount());
            ticketEntity.setPrice(ticketDto.getPrice());
            ticketEntity.setEvent(eventEntity);
            ticketEntities.add(ticketEntity);
        }
        eventEntity.setTickets(ticketEntities);
        eventRepository.saveAndFlush(eventEntity);
        return new EventDto(eventEntity);

    }

    /**
     * Deletes an event from the database, if it exists.
     *
     * @param id the event ID that is being deleted.
     * @return boolean - true if the event was deleted successfully, otherwise false.
     */
    public boolean deleteEvent(Long id) {
        if (eventRepository.findById(id).isEmpty()) {
            return false;
        }

        eventRepository.deleteById(id);
        return true;
    }

    /**
     * Finds all events from a city in database and returns them.
     *
     * @param city the city
     * @return the events in that city
     */
    public List<EventDto> getEventsByCity(String city) {
        List<EventEntity> eventEntities = eventRepository.findAllByLocation_City(city);
        return eventEntities.stream().map(EventDto::new).toList();
    }

    public List<String> getAllCategories() {
        return eventRepository.findAll().stream().map(EventEntity::getCategory).distinct().sorted().toList();
    }


}
