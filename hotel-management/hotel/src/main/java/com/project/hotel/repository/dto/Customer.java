package com.project.hotel.repository.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class Customer {

    private String seq;

    @NotEmpty(message = "이름은 필수 입력 값입니다.")
    @Pattern(regexp = "^[가-힣]{2,5}$", message = "이름은 2~5자 이내 한글로 입력해주세요.")
    private String name;

    @NotEmpty(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9]{6,12}$", message = "아이디는 문자, 숫자를 포함하여 6~12자 이내로 입력해주세요.")
    private String id;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9]{6,12}$", message = "비밀번호는 문자, 숫자를 포함하여 6~12자 이내로 입력해주세요.")
    private String pw;

    @NotEmpty(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호는 010-0000-0000 형태로 입력해주세요.")
    private String tel;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

}
