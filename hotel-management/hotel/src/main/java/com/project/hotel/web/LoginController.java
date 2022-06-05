package com.project.hotel.web;

import com.project.hotel.domain.customer.Customer;
import com.project.hotel.domain.customer.CustomerRepository;
import com.project.hotel.domain.login.LoginService;
import com.project.hotel.web.dto.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final CustomerRepository customerRepository;
    private final LoginService loginService;


    @GetMapping("customer/login")
    public String loginGet(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "customer/login";
    }

    @PostMapping("customer/login")
    public String loginPost(@ModelAttribute @Validated LoginForm loginForm, BindingResult bindingResult, Model model, HttpServletRequest request) {

        // 필드 에러가 있는 경우
        if (bindingResult.hasErrors()) {
            return "customer/login";
        }

        // 로그인 로직 실행
        Customer loginCustomer = loginService.login(loginForm.getId(), loginForm.getPw());

        // 로그인 실패 시
        if (loginCustomer == null) {
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 올바르지 않습니다.");
            return "customer/login";
        }

        // 로그인 성공 시 세션 생성 후 뷰로 전달
        HttpSession session = request.getSession();
        session.setAttribute("loginCustomer", loginCustomer);
        model.addAttribute(session);

        return "redirect:/customer/main";
    }
}
