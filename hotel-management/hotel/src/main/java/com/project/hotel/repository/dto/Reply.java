package com.project.hotel.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Reply {
    private String seq;
    private String rv_seq;
    private String content;
    private String regdate;
}
