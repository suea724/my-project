package com.project.hotel.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewBoardDto {

    private String seq;
    private String content;
    private String rating;
    private String checkIn;
    private String checkOut;
    private String type;

    @Builder
    public ReviewBoardDto(String seq, String content, String rating, String checkIn, String checkOut, String type) {
        this.seq = seq;
        this.content = content;
        this.rating = rating;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.type = type;
    }
}
