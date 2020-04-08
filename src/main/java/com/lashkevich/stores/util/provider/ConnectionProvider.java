package com.lashkevich.stores.util.provider;

import com.lashkevich.stores.exception.ConnectionStoreException;

import java.sql.Connection;

public interface ConnectionProvider {
    Connection getConnection() throws ConnectionStoreException;

    boolean getCommitStatus();
}
