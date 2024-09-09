package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDetails {
    static private String URL="jdbc:mysql://localhost:3306/Library";
    static private String User="root";
    static private String pass="root";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,User,pass);
    }
}
