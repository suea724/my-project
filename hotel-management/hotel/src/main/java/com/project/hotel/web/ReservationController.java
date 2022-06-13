package com.project.hotel.web;

import com.project.hotel.domain.customer.Customer;
import com.project.hotel.service.ReservationService;
import com.project.hotel.service.ReviewService;
import com.project.hotel.web.dto.ReservationDto;
import com.project.hotel.web.dto.ReviewRequestDto;
import com.project.hotel.web.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReviewService reviewService;

    @GetMapping("/customer/reservation")
    public String reservationList(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Customer loginCustomer = (Customer) session.getAttribute(SessionConst.LOGIN_CUSTOMER);
        model.addAttribute("reservationList", reservationService.findReservationList(loginCustomer.getSeq()));
        return "customer/reservationList";
    }

    @GetMapping("/customer/reservation/{seq}")
    public String reviewGet(@PathVariable String seq, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        Customer loginCustomer = (Customer) session.getAttribute(SessionConst.LOGIN_CUSTOMER);
        ReservationDto reservationDto = reservationService.findReservation(loginCustomer.getSeq(), seq);

        if (reservationDto == null) { // 다른 고객의 예약을 접근하려는 경우 리다이렉트
            return "redirect:/customer/reservation";
        }

        model.addAttribute(reservationDto); // 예약 정보 전달

        ReviewResponseDto reviewResponseDto = reviewService.findByReservationSeq(seq);

        if (reviewResponseDto != null) {
            model.addAttribute(reviewResponseDto); // 작성한 리뷰가 있으면 리뷰 내용 페이지로 이동 
            return "customer/readReview";
        }

        model.addAttribute("reviewRequestDto", ReviewRequestDto.builder().build());
        return "customer/createReview";
    }

    @PostMapping("/customer/reservation/{seq}")
    public String reviewPost(@PathVariable String seq, @Validated @ModelAttribute ReviewRequestDto reviewRequestDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            ReservationDto reservationDto = reservationService.findBySeq(seq); // 사용자 인증 정보까지는 필요없으므로 예약 번호로 DTO를 찾음
            model.addAttribute("reservationDto", reservationDto);
            return "/customer/createReview";
        }

        reviewService.save(seq, reviewRequestDto);
        return "redirect:/customer/reservation";
    }

}
