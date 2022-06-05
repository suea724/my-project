package com.project.hotel.domain;

import lombok.Getter;

@Getter
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
