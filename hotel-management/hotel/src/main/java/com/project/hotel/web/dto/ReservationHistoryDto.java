package com.project.hotel.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservationHistoryDto {

    private String seq;
    private String reservationDate;
    private String roomType;
    private String guests;
    private String checkIn;
    private String checkOut;
    private String status;

    @Builder
    public ReservationHistoryDto(String seq, String reservationDate, String roomType, String guests, String checkIn, String checkOut, String status) {
        this.seq = seq;
        this.reservationDate = reservationDate;
        this.roomType = roomType;
        this.guests = guests;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }
}
