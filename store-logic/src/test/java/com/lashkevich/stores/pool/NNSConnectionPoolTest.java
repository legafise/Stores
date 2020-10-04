package com.lashkevich.stores.pool;

import com.lashkevich.stores.exception.NNSConnectionPoolException;
import com.lashkevich.stores.util.reader.impl.NNSTestPropertiesReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class NNSConnectionPoolTest {
    @Before
    public void setUp() throws NNSConnectionPoolException {
        NNSConnectionPool.getInstance().setPropertiesReader(new NNSTestPropertiesReader());
        NNSConnectionPool.getInstance().initializeConnectionPool(2);
    }

    @Test
    public void initializeConnectionPool() {
        Assert.assertEquals(2, NNSConnectionPool.getInstance().getFreeConnectionsSize());
    }

    @Test
    public void acquireConnectionPositiveTest() throws NNSConnectionPoolException {
        NNSConnectionPool.getInstance().acquireConnection();
        Assert.assertEquals(1, NNSConnectionPool.getInstance().getFreeConnectionsSize());
        Assert.assertEquals(1, NNSConnectionPool.getInstance().getBusyConnectionsSize());
    }

    @Test(expected = NNSConnectionPoolException.class)
    public void acquireConnectionNegativeTest() throws NNSConnectionPoolException {
        NNSConnectionPool.getInstance().closeConnections();
        NNSConnectionPool.getInstance().acquireConnection();
    }

    @Test
    public void putBackConnectionPositiveTest() throws NNSConnectionPoolException {
        Connection connection = NNSConnectionPool.getInstance().acquireConnection();
        NNSConnectionPool.getInstance().putBackConnection(connection);
        Assert.assertEquals(2, NNSConnectionPool.getInstance().getFreeConnectionsSize());
    }

    @Test(expected = NNSConnectionPoolException.class)
    public void putBackConnectionNegativeTest() throws NNSConnectionPoolException {
        NNSConnectionPool.getInstance().putBackConnection(null);
    }

    @Test
    public void closeConnections() throws NNSConnectionPoolException {
        NNSConnectionPool.getInstance().closeConnections();
        Assert.assertEquals(0, NNSConnectionPool.getInstance().getFreeConnectionsSize());
        Assert.assertEquals(0, NNSConnectionPool.getInstance().getBusyConnectionsSize());
    }
}