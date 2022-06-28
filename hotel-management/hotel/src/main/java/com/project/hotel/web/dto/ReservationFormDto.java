package com.project.hotel.web.dto;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

@Getter
public class ReservationFormDto {

    @NotEmpty
    private String checkIn;
    @NotEmpty
    private String checkOut;
    @Range(min = 1, max = 6)
    private Integer guests;

    @Builder
    public ReservationFormDto(String checkIn, String checkOut, Integer guests) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guests = guests;
    }
}
