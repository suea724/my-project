package com.project.hotel.domain.customer;

import com.project.hotel.domain.DBUtil;
import com.project.hotel.domain.Review;
import com.project.hotel.web.dto.ReviewBoardDto;
import com.project.hotel.web.dto.ReviewDto;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

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

    public void delete(String seq) {
        try {
            Connection CONN = DBUtil.open();
            String sql = "delete from review_tb where rsv_seq = ?";
            PreparedStatement pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, seq);
            pstmt.executeUpdate();

            pstmt.close();
            DBUtil.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ReviewBoardDto> findReviewList() {

        ArrayList<ReviewBoardDto> list = new ArrayList<>();

        try {

            Connection conn = DBUtil.open();
            Statement stmt = conn.createStatement();
            String sql = "select rv.seq, rv.content,rv.rating, rsv.check_in, rsv.check_out, t.type from review_tb rv \n" +
                    "    inner join reservation_tb rsv \n" +
                    "        on rv.rsv_seq = rsv.seq \n" +
                    "            inner join type_tb t\n" +
                    "                on rsv.type_seq = t.seq\n" +
                    "                    order by check_out desc";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {

                String rating = "";

                for (int i = 0 ; i < rs.getInt("rating") ; i ++) {
                    rating += "⭐";
                }
                ReviewBoardDto dto = ReviewBoardDto.builder()
                                                    .seq(rs.getString("seq"))
                                                    .content(rs.getString("content"))
                                                    .rating(rating)
                                                    .checkIn(rs.getString("check_in"))
                                                    .checkOut(rs.getString("check_out"))
                                                    .type(rs.getString("type"))
                                                    .build();

                list.add(dto);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
