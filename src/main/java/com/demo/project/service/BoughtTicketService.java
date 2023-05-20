package com.demo.project.service;

import com.demo.project.dto.BoughtTicketDto;
import com.demo.project.dto.EventDto;
import com.demo.project.dto.TicketDetailsDto;
import com.demo.project.entity.BoughtTicketEntity;
import com.demo.project.entity.EventEntity;
import com.demo.project.entity.TicketEntity;
import com.demo.project.repository.BoughtTicketRepository;
import com.demo.project.repository.EventRepository;
import com.demo.project.repository.StandardUserRepository;
import com.demo.project.repository.TicketRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class BoughtTicketService implements BoughtTicketServiceInterface {

    @Autowired
    private BoughtTicketRepository boughtTicketRepository;


    @Autowired
    private StandardUserRepository standardUserRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;




//    @Transactional
    public int buyTickets(List<BoughtTicketDto> boughtTickets) {
        int nr = 0;
        for (BoughtTicketDto boughtTicket : boughtTickets) {
            BoughtTicketEntity boughtTicketEntity = new BoughtTicketEntity();
            boughtTicketEntity.setUser(this.standardUserRepository.getById(boughtTicket.getUserId()));
            TicketEntity ticketEntity = this.ticketRepository.getById(boughtTicket.getTypedTicketId());
            EventEntity eventEntity = ticketEntity.getEvent();
            eventEntity.setSoldTickets(eventEntity.getSoldTickets()+1);

            boughtTicketEntity.setTypedTicket(ticketEntity);

            boughtTicketRepository.saveAndFlush(boughtTicketEntity);
            eventRepository.saveAndFlush(eventEntity);
            nr++;
        }
        return nr;
    }

    @Override
    public List<TicketDetailsDto> getTicketsByUserId(Long id) {
        List<BoughtTicketEntity> boughtTicketEntities =  boughtTicketRepository.getBoughtTicketEntitiesByUser_Id(id);

        List<TicketDetailsDto> ticketDetailsDtos = new ArrayList<>();

        for(BoughtTicketEntity b: boughtTicketEntities){
            TicketEntity typedTicket = b.getTypedTicket();
            EventEntity event = typedTicket.getEvent();
            TicketDetailsDto ticketDetailsDto = new TicketDetailsDto(b.getId(),typedTicket.getPrice(), new EventDto(event));
            ticketDetailsDtos.add(ticketDetailsDto);
        }

        return ticketDetailsDtos;

    }
}
