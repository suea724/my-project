package com.project.hotel.domain.customer;

import com.project.hotel.domain.DBUtil;
import com.project.hotel.web.dto.ReservationListDto;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
@Getter
public class CustomerRepository {

    // 모든 고객 데이터를 ArrayList로 반환
    public ArrayList<Customer> findAll() {

        ArrayList<Customer> list = new ArrayList<>();
        try {

            Connection CONN = DBUtil.open();

            Statement stmt = CONN.createStatement();

            String sql = "select * from Customer_TB";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {

                Customer customer = Customer.builder()
                                    .seq(rs.getString("seq"))
                                    .name(rs.getString("name"))
                                    .id(rs.getString("id"))
                                    .pw(rs.getString("pw"))
                                    .email(rs.getString("email"))
                                    .tel(rs.getString("tel")).build();

                list.add(customer);
            }
            DBUtil.close();
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 고객 데이터 저장
    public Customer save(Customer customer) {

        try {
            Connection CONN = DBUtil.open();

            String sql = "insert into Customer_TB values (customer_seq.nextval, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getId());
            pstmt.setString(3, customer.getPw());
            pstmt.setString(4, customer.getTel());
            pstmt.setString(5, customer.getEmail());

            pstmt.executeUpdate();

            pstmt.close();
            DBUtil.close();

            return customer;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 고유 번호로 고객 찾기
    public Customer findBySeq(String seq) {

        Customer customer = null;

        try {
            Connection CONN = DBUtil.open();

            String sql = "select * from Customer_TB where seq = ?";
            PreparedStatement pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, seq);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                customer = Customer.builder()
                        .seq(rs.getString("seq"))
                        .name(rs.getString("name"))
                        .id(rs.getString("id"))
                        .pw(rs.getString("pw"))
                        .email(rs.getString("email"))
                        .tel(rs.getString("tel")).build();

                rs.close();
                pstmt.close();
                DBUtil.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    // 회원가입 시 입력한 아이디로 고객 찾기
    public Customer findById(String id) {

        Customer customer = null;

        try {
            Connection CONN = DBUtil.open();

            String sql = "select seq, name, id, pw, email, tel from Customer_TB where id = ?";
            PreparedStatement preparedStatement = CONN.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                customer = Customer.builder()
                        .seq(rs.getString("seq"))
                        .name(rs.getString("name"))
                        .id(rs.getString("id"))
                        .pw(rs.getString("pw"))
                        .email(rs.getString("email"))
                        .tel(rs.getString("tel")).build();

            }
            DBUtil.close();
            rs.close();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    // 회원가입 시 입력한 아이디로 고객 정보 지우기
    public void deleteCustomer(String id) {
        try {
            Connection CONN = DBUtil.open();

            String sql = "delete from Customer_TB where id = ?";
            PreparedStatement pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();

            pstmt.close();
            DBUtil.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ReservationListDto> findReservationList(String seq) {
        ArrayList<ReservationListDto> list = new ArrayList<>();

        try {
            Connection CONN = DBUtil.open();

            String sql = "select rsv.rsv_date, rm.type, rsv.guests, rsv.check_in, rsv.check_out, rsv.status " +
                    "from Reservation_TB rsv " +
                    "inner join Type_TB rm " +
                    "on rsv.type_seq = rm.seq " +
                    "where cust_seq = ? " +
                    "order by rsv_date desc";

            PreparedStatement pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, seq);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                ReservationListDto dto = ReservationListDto.builder()
                                                                .reservationDate(rs.getString("rsv_date"))
                                                                .roomType(rs.getString("type"))
                                                                .guests(rs.getString("guests"))
                                                                .checkIn(rs.getString("check_in"))
                                                                .checkOut(rs.getString("check_out"))
                                                                .status(rs.getString("status"))
                                                                .build();
                list.add(dto);
            }

            pstmt.close();
            rs.close();
            DBUtil.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
