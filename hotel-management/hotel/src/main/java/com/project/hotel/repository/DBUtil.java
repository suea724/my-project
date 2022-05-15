package com.project.hotel.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static Connection conn = null;

    public static Connection open() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "hotel";
        String pw = "hotel";

        try {
            conn = DriverManager.getConnection(url, id, pw);
            Class.forName("oracle.jdbc.driver.OracleDriver");

            return conn;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close() {
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
