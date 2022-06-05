package com.project.hotel.web;

import com.project.hotel.domain.customer.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping // 메인 페이지
    public String home() {
        return "home";
    }

    @GetMapping("customer/main") // 로그인한 고객 페이지
    public String customerMain(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        // 세션 정보가 없으면 home으로
        if (session == null) {
            return "home";
        }

        // 세션이 있는 경우 세션으로부터 로그인한 고객 객체를 view에 전달
        Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
        model.addAttribute(loginCustomer);
        return "customer/main";
    }
}
