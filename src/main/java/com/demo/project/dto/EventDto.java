package com.demo.project.dto;

import com.demo.project.entity.EventEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

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

    private List<TicketDto> tickets;

    public EventDto(EventEntity eventEntity) {
        this.name = eventEntity.getName();
        this.description = eventEntity.getDescription();
        this.category = eventEntity.getCategory();
        this.date = eventEntity.getDate();
        this.hour = eventEntity.getHour();
        this.organizerId = eventEntity.getOrganizer().getId();
        this.locationId = eventEntity.getLocation().getId();
        this.tickets = eventEntity.getTickets().stream().map(TicketDto::new).collect(Collectors.toList());

    }


}
