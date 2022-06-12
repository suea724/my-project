package com.project.hotel.service;

import com.project.hotel.domain.Review;
import com.project.hotel.domain.customer.ReviewRepository;
import com.project.hotel.web.dto.ReviewRequestDto;
import com.project.hotel.web.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void save(String reservationSeq, ReviewRequestDto reviewDto) {
        Review review = Review.builder()
                                .reservationSeq(reservationSeq)
                                .rating(reviewDto.getRating())
                                .content(reviewDto.getContent())
                                .build();
        reviewRepository.save(review);
    }

    public ReviewResponseDto findByReservationSeq(String seq) {
        Review review = reviewRepository.findByReservationSeq(seq);

        if (review == null) {
            return null;
        }

        ReviewResponseDto responseDto = ReviewResponseDto.builder()
                                                            .rating(review.getRating())
                                                            .content(review.getContent())
                                                            .regdate(review.getRegdate())
                                                            .build();
        return responseDto;
    }

}
