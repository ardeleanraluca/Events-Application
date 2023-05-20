package com.demo.project.controller;

import com.demo.project.dto.BoughtTicketDto;
import com.demo.project.dto.TicketDetailsDto;
import com.demo.project.service.BoughtTicketServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boughtTicket")
public class BoughtTicketController {
    @Autowired
    private BoughtTicketServiceInterface boughtTicketServiceInterface;

    @PostMapping("/buy")
    public ResponseEntity<String> buyTickets(@RequestBody List<BoughtTicketDto> boughtTicketsDto) {
        int savedTickets = boughtTicketServiceInterface.buyTickets(boughtTicketsDto);
        if (savedTickets != boughtTicketsDto.size()) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }

    @GetMapping("/myTickets")
    public ResponseEntity<List<TicketDetailsDto>> getTicketsByUserId( Long id) {
        List<TicketDetailsDto> tickets = boughtTicketServiceInterface.getTicketsByUserId(id);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

}
