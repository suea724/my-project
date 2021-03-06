package com.project.hotel.web;

import com.project.hotel.domain.customer.Customer;
import com.project.hotel.domain.customer.CustomerRepository;
import com.project.hotel.service.JoinService;
import com.project.hotel.web.dto.JoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("customer/join")
    public String joinGet(@ModelAttribute("joinForm") JoinForm joinForm) {
        return "customer/join";
    }

    @PostMapping("customer/join")
    public String joinPost(@ModelAttribute @Validated JoinForm joinForm, BindingResult bindingResult) {

        // 필드 에러가 있는 경우
        if (bindingResult.hasErrors()) {
            return "customer/join";
        }

        // 중복된 아이디가 존재하는 경우
        if (joinService.isDuplicate(joinForm)) {
            bindingResult.reject("idAlreadyExist", "중복된 아이디가 존재합니다. 다른 아이디를 입력해주세요.");
            return "customer/join";
        }

        joinService.join(joinForm);

        return "redirect:/customer/login";
    }
}
