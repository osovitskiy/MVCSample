package com.github.osovitskiy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static String url = "jdbc:jtds:sqlserver://localhost:1433;instance=SQLEXPRESS;DatabaseName=MVCSample";

    private static String username = "user";
    private static String password = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
