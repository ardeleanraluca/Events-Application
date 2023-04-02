package com.demo.project.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    @NotEmpty
    private String name;

    private String description;
    @NotEmpty
    private String category;
    @NotEmpty
    private String date;

    private String hour;

    private Long organizerId;
    private Long locationId;
    private Boolean moreCategoryTickets;

    private List<TicketDto> tickets;
}
