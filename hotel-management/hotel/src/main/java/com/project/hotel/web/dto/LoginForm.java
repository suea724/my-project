package com.project.hotel.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginForm {

    @NotNull
    private String id;

    @NotNull
    private String pw;
}
