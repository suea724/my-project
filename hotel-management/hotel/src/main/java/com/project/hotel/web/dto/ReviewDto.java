package com.project.hotel.web.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
//@NoArgsConstructor
public class ReviewDto {

    @NotNull(message = "별점을 선택해주세요.")
    private String rating;
    @NotEmpty(message = "리뷰 내용은 필수 항목입니다.")
    private String content;

    @Builder
    public ReviewDto(String rating, String content) {
        this.rating = rating;
        this.content = content;
    }
}
