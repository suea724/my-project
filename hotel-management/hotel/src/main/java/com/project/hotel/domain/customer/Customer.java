package com.project.hotel.domain.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Customer {

    private String seq;
    private String name;
    private String id;
    private String pw;
    private String tel;
    private String email;

    @Builder
    public Customer(String seq, String name, String id, String pw, String tel, String email) {
        this.seq = seq;
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.tel = tel;
        this.email = email;
    }
}
