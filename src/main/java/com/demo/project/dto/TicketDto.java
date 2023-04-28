package com.demo.project.dto;

import com.demo.project.entity.TicketEntity;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TicketDto {

    private Double price;

    private Double discount;

    public TicketDto(TicketEntity ticketEntity) {
        this.price = ticketEntity.getPrice();
        this.discount = ticketEntity.getDiscount();
    }


}
