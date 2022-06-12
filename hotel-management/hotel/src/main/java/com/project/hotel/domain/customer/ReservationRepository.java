package com.project.hotel.domain.customer;

import com.project.hotel.domain.DBUtil;
import com.project.hotel.web.dto.ReservationDto;
import com.project.hotel.web.dto.ReservationListDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class ReservationRepository {

    public ReservationDto findBySeq(String seq) {

        ReservationDto reservationDto = null;

        try {
            Connection CONN = DBUtil.open();
            String sql = "select cust_seq, rsv.seq, rsv.rsv_date, rm.type, rsv.guests, rsv.check_in, rsv.check_out, rsv.status " +
                    "from Reservation_TB rsv " +
                    "inner join Type_TB rm " +
                    "on rsv.type_seq = rm.seq " +
                    "where rsv.seq = ?";

            PreparedStatement pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, seq);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                reservationDto = ReservationDto.builder()
                        .seq(rs.getString("seq"))
                        .customerSeq(rs.getString("cust_seq"))
                        .reservationDate(rs.getString("rsv_date"))
                        .roomType(rs.getString("type"))
                        .guests(rs.getString("guests"))
                        .checkIn(rs.getString("check_in"))
                        .checkOut(rs.getString("check_out"))
                        .status(rs.getString("status"))
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservationDto;
    }
}

