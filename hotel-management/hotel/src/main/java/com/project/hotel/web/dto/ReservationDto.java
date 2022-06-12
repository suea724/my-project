package com.project.hotel.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservationDto {

    private String seq;
    private String customerSeq;
    private String reservationDate;
    private String roomType;
    private String guests;
    private String checkIn;
    private String checkOut;
    private String status;

    @Builder
    public ReservationDto(String seq, String customerSeq, String reservationDate, String roomType, String guests, String checkIn, String checkOut, String status) {
        this.seq = seq;
        this.customerSeq = customerSeq;
        this.reservationDate = reservationDate;
        this.roomType = roomType;
        this.guests = guests;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }
}