package com.project.hotel.domain.customer;

import com.project.hotel.domain.DBUtil;
import com.project.hotel.web.dto.RoomTypeDTO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Repository
public class TypeRepository {

    public ArrayList<RoomTypeDTO> findRooms(String guests) {
        try {
            Connection conn = DBUtil.open();
            String sql = "select * from type_tb where capacity >= ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, guests);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<RoomTypeDTO> list = new ArrayList<>();

            while(rs.next()) {
                RoomTypeDTO dto = RoomTypeDTO.builder()
                                    .seq(rs.getString("seq"))
                                    .type(rs.getString("type"))
                                    .capacity(rs.getString("capacity"))
                                    .rate(rs.getInt("rate"))
                                    .build();
                list.add(dto);
            }

            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
