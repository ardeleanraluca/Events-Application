package com.demo.project.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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

    private OrganizerDto organizer;
//
//    private HallEntity hall;
//
//    private Set<TicketEntity> tickets;

}
