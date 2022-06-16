package com.project.hotel.service;

import com.project.hotel.domain.Review;
import com.project.hotel.domain.customer.ReviewRepository;
import com.project.hotel.web.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void save(String reservationSeq, ReviewDto reviewDto) {
        Review review = Review.builder()
                                .reservationSeq(reservationSeq)
                                .rating(reviewDto.getRating())
                                .content(reviewDto.getContent())
                                .build();
        reviewRepository.save(review);
    }

    public ReviewDto findByReservationSeq(String seq) {
        Review review = reviewRepository.findByReservationSeq(seq);

        if (review == null) {
            return null;
        }

        ReviewDto reviewDto = ReviewDto.builder()
                                    .rating(review.getRating())
                                    .content(review.getContent())
                                    .build();
        return reviewDto;
    }

    public void update(String seq, ReviewDto reviewDto) {
        reviewRepository.update(seq, reviewDto);
    }

    public void delete(String seq) {
        reviewRepository.delete(seq);
    }

}
