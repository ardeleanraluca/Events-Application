package com.demo.project.service;

import com.demo.project.dto.BoughtTicketDto;

import java.util.List;

public interface BoughtTicketServiceInterface {

    public int buyTickets(List<BoughtTicketDto> boughtTickets);

}
