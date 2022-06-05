package com.project.hotel.domain.customer;

import com.project.hotel.domain.DBUtil;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
@Getter
public class CustomerRepository {

    private final Connection CONN = DBUtil.open();

    // 모든 고객 데이터를 ArrayList로 반환
    public ArrayList<Customer> findAll() {
        try {

            ArrayList<Customer> list = new ArrayList<>();

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
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 고객 데이터 저장
    public Customer save(Customer customer) {

        try {
            String sql = "insert into Customer_TB values (customer_seq.nextval, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getId());
            pstmt.setString(3, customer.getPw());
            pstmt.setString(4, customer.getTel());
            pstmt.setString(5, customer.getEmail());

            pstmt.executeUpdate();
            return customer;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 고유 번호로 고객 찾기
    public Customer findBySeq(String seq) {

        try {
            String sql = "select * from Customer_TB where seq = ?";
            PreparedStatement pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, seq);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                Customer customer = Customer.builder()
                        .seq(rs.getString("seq"))
                        .name(rs.getString("name"))
                        .id(rs.getString("id"))
                        .pw(rs.getString("pw"))
                        .email(rs.getString("email"))
                        .tel(rs.getString("tel")).build();

                return customer;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 회원가입 시 입력한 아이디로 고객 찾기
    public Customer findById(String id) {

        try {
            String sql = "select * from Customer_TB where id = ?";
            PreparedStatement pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                Customer customer = Customer.builder()
                        .seq(rs.getString("seq"))
                        .name(rs.getString("name"))
                        .id(rs.getString("id"))
                        .pw(rs.getString("pw"))
                        .email(rs.getString("email"))
                        .tel(rs.getString("tel")).build();

                return customer;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 회원가입 시 입력한 아이디로 고객 정보 지우기
    public void deleteCustomer(String id) {
        try {
            String sql = "delete from Customer_TB where id = ?";
            PreparedStatement pstmt = CONN.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
