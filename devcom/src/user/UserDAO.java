package user;

import common.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class UserDAO {

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public UserDAO() {
        conn = DBUtil.open();
    }

    public int add(UserDTO dto) {
        try {
            String sql = "insert into tblUser(id, pw, name, grade, regdate, pic) values(?, ?, ?, default, default, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getId());
            pstmt.setString(2, dto.getPw());
            pstmt.setString(3, dto.getName());
            pstmt.setString(4, dto.getPic());

            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public UserDTO login(String id, String pw) {
        try {
            String sql = "select * from tblUser where id = ? and pw = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();

            UserDTO dto = null;

            if (rs.next()) {
                dto = UserDTO.builder()
                        .id(id)
                        .pw(pw)
                        .name(rs.getString("name"))
                        .grade(rs.getString("grade"))
                        .regdate(rs.getString("regdate"))
                        .pic(rs.getString("pic"))
                        .build();
            }

            return dto;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDTO get(String id) {
        try {
            String sql = "select * from tblUser where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();

            UserDTO dto = null;

            if (rs.next()) {
                dto = UserDTO.builder()
                        .id(id)
                        .pw(rs.getString("pw"))
                        .name(rs.getString("name"))
                        .grade(rs.getString("grade"))
                        .regdate(rs.getString("regdate"))
                        .pic(rs.getString("pic"))
                        .build();
            }

            return dto;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int del(String id) {
        try {
            String sql = "delete from tblUser where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
