package com.lashkevich.stores.pool;

import com.lashkevich.stores.exception.ConnectionPoolException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static com.lashkevich.stores.constant.PoolConstant.*;

public class ConnectionPoolTest {
    @Before
    public void setUp() throws ConnectionPoolException {
        ConnectionPool.getInstance().initializeConnectionPool(2, URL, USER, PASSWORD);
    }

    @Test
    public void initializeConnectionPool() throws ConnectionPoolException {
        Assert.assertEquals(2, ConnectionPool.getInstance().getFreeConnectionsSize());
    }

    @Test
    public void acquireConnectionPositiveTest() throws ConnectionPoolException {
        ConnectionPool.getInstance().acquireConnection();
        Assert.assertEquals(1, ConnectionPool.getInstance().getFreeConnectionsSize());
        Assert.assertEquals(1, ConnectionPool.getInstance().getBusyConnectionsSize());
    }

    @Test (expected = ConnectionPoolException.class)
    public void acquireConnectionNegativeTest() throws ConnectionPoolException {
        ConnectionPool.getInstance().closeConnections();
        ConnectionPool.getInstance().acquireConnection();
    }

    @Test
    public void putBackConnectionPositiveTest() throws ConnectionPoolException {
        Connection connection = ConnectionPool.getInstance().acquireConnection();
        ConnectionPool.getInstance().putBackConnection(connection);
        Assert.assertEquals(2, ConnectionPool.getInstance().getFreeConnectionsSize());
    }

    @Test (expected = ConnectionPoolException.class)
    public void putBackConnectionNegativeTest() throws ConnectionPoolException {
        ConnectionPool.getInstance().putBackConnection(null);
    }

    @Test
    public void closeConnections() throws ConnectionPoolException {
        ConnectionPool.getInstance().closeConnections();
        Assert.assertEquals(0, ConnectionPool.getInstance().getFreeConnectionsSize());
        Assert.assertEquals(0, ConnectionPool.getInstance().getBusyConnectionsSize());
    }
}