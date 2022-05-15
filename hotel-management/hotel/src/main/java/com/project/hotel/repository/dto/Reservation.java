package com.project.hotel.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Reservation {

    private String seq;
    private String cust_seq;
    private String type_seq;
    private String rsv_date;
    private int guests;
    private String check_in;
    private String check_out;
    private String status;
}
