package com.project.hotel.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomTypeDTO {

    private String seq;
    private String type;
    private String capacity;
    private Integer rate;

    @Builder
    public RoomTypeDTO(String seq, String type, String capacity, Integer rate) {
        this.seq = seq;
        this.type = type;
        this.capacity = capacity;
        this.rate = rate;
    }
}
