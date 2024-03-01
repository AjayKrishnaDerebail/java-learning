package org.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FirstJdbcProgram {
    public static void connectionCreation() throws SQLException {
        Connection conn = null;
        String url = "jdbc:mysql://127.0.0.1:3306/student";
        String username = "";
        String password = "";
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(conn == null) {
                System.out.println("NPE thrown");
            } else {
                conn.close();
            }
        }
    }

    public static void main (String[] args) {
        System.out.println("Hello world!");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connectionCreation();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
