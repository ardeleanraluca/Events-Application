package com.demo.project.service;

import com.demo.project.dto.BoughtTicketDto;
import com.demo.project.entity.BoughtTicketEntity;
import com.demo.project.repository.BoughtTicketRepository;
import com.demo.project.repository.StandardUserRepository;
import com.demo.project.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


    @Transactional
    public int buyTickets(List<BoughtTicketDto> boughtTickets) {
        int nr = 0;
        for (BoughtTicketDto boughtTicket : boughtTickets) {
            BoughtTicketEntity boughtTicketEntity = new BoughtTicketEntity();
            boughtTicketEntity.setUser(this.standardUserRepository.getById(boughtTicket.getUserId()));
            boughtTicketEntity.setTypedTicket(this.ticketRepository.getById(boughtTicket.getTypedTicketId()));

            boughtTicketRepository.saveAndFlush(boughtTicketEntity);
            nr++;
        }
        return nr;
    }
}
