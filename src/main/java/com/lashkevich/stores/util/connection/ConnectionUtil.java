package com.lashkevich.stores.util.connection;

import com.lashkevich.stores.exception.ConnectionStoreException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/nonamestore";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws ConnectionStoreException {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConnectionStoreException(e);
        }
    }
}