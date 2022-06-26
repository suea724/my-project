package com.project.hotel.web;

import com.project.hotel.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ReviewBoardController {

    private final ReviewService reviewService;

    @GetMapping("/customer/review")
    public String reviewBoard(Model model) {
        model.addAttribute( "reviewList", reviewService.findReviewList());
        return "customer/reviewBoard";
    }
}
