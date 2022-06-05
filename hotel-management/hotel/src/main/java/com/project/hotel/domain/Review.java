package com.project.hotel.domain;

import lombok.Getter;

@Getter
public class Review {
    private String seq;
    private String rsv_seq;
    private String rating;
    private String content;
    private String regdate;
}
