package com.project.hotel.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Review {
    private String seq;
    private String reservationSeq;
    private String rating;
    private String content;
    private String regdate;

    @Builder
    public Review(String seq, String reservationSeq, String rating, String content, String regdate) {
        this.seq = seq;
        this.reservationSeq = reservationSeq;
        this.rating = rating;
        this.content = content;
        this.regdate = regdate;
    }
}
