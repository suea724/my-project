package com.project.hotel.web;

import com.project.hotel.domain.customer.Customer;
import com.project.hotel.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/customer/reservationList")
    public String reservationList(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Customer loginCustomer = (Customer) session.getAttribute(SessionConst.LOGIN_CUSTOMER);
        model.addAttribute("reservationList", reservationService.findReservationList(loginCustomer.getSeq()));
        return "customer/reservationList";
    }
}
