package com.project.hotel.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Review {
    private String seq;
    private String rsv_seq;
    private String rating;
    private String content;
    private String regdate;
}
