package org.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private ConnectionProvider(){

    }
    public static Connection getConnection () throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/student";
        String username = "ajaykdbadmin";
        String password = "Derebail@1234";
        Connection conn = DriverManager.getConnection(url, username, password);

        if(conn.isClosed()) {
            System.out.println("Connection is closed");
        } else {
            System.out.println("Connection created");
        }

        return conn;

    }
}