package com.lashkevich.stores.util.provider.impl;

import com.lashkevich.stores.exception.ConnectionStoreException;
import com.lashkevich.stores.util.provider.ConnectionProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProviderImpl implements ConnectionProvider {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/nonamestore";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public Connection getConnection() throws ConnectionStoreException {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConnectionStoreException(e);
        }
    }

    @Override
    public boolean getCommitStatus() {
        return true;
    }
}