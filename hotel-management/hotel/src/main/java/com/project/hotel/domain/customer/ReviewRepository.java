package com.project.hotel.domain.customer;

import com.project.hotel.domain.DBUtil;
import com.project.hotel.domain.Review;
import com.project.hotel.web.dto.ReviewDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class ReviewRepository {

    public void save(Review review) {
        try {
            Connection CONN = DBUtil.open();
            String sql = "insert into Review_TB(seq, rsv_seq, rating, content) values(review_seq.nextVal, ?, ?, ?)";
            PreparedStatement pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, review.getReservationSeq());
            pstmt.setString(2, review.getRating());
            pstmt.setString(3, review.getContent());
            pstmt.executeUpdate();

            pstmt.close();
            DBUtil.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Review findByReservationSeq(String seq) {
        Review review = null;

        try {
            Connection CONN = DBUtil.open();
            String sql = "select * from Review_TB where rsv_seq = ?";
            PreparedStatement pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, seq);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                review = Review.builder()
                                .seq(rs.getString("seq"))
                                .reservationSeq(rs.getString("rsv_seq"))
                                .rating(rs.getString("rating"))
                                .content(rs.getString("content"))
                                .regdate(rs.getString("regdate"))
                                .build();
            }
            pstmt.close();
            DBUtil.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return review;
    }

    public void update(String seq, ReviewDto reviewDto) {
        try {
            Connection CONN = DBUtil.open();
            String sql = "update review_tb set rating = ?, content = ? where rsv_seq = ?";
            PreparedStatement pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, reviewDto.getRating());
            pstmt.setString(2, reviewDto.getContent());
            pstmt.setString(3, seq);
            pstmt.executeUpdate();

            pstmt.close();
            DBUtil.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
