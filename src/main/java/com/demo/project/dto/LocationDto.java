package com.demo.project.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    @NotEmpty
    private Long noSeats;

    @NotEmpty
    private String address;

    @NotEmpty
    private String name;
    @NotEmpty
    private String county;
    @NotEmpty
    private String city;

}
