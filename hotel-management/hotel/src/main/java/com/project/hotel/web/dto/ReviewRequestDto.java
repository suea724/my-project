package com.project.hotel.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
//@NoArgsConstructor
public class ReviewRequestDto {

    @NotNull()
    private String rating;
    @NotNull()
    private String content;

    @Builder
    public ReviewRequestDto(String rating, String content) {
        this.rating = rating;
        this.content = content;
    }
}
