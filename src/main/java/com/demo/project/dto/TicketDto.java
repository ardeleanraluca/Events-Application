package com.demo.project.dto;

import com.demo.project.entity.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private Double price;

    private Double discount;

    public TicketDto(TicketEntity ticketEntity) {
        this.price = ticketEntity.getPrice();
        this.discount = ticketEntity.getDiscount();
    }


}
