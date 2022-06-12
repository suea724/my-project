package com.project.hotel.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewResponseDto {

    private String rating;
    private String content;
    private String regdate;

    @Builder
    public ReviewResponseDto(String rating, String content, String regdate) {
        this.rating = rating;
        this.content = content;
        this.regdate = regdate;
    }
}
