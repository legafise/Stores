package com.lashkevich.stores.pool;

import com.lashkevich.stores.exception.NNSConnectionPoolException;
import com.lashkevich.stores.exception.NNSUtilException;
import com.lashkevich.stores.util.reader.PropertiesReader;
import com.lashkevich.stores.util.reader.impl.NNSProdPropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NNSConnectionPool {
    private static final AtomicBoolean INSTANCE_CREATED = new AtomicBoolean(false);
    private static final String PROPERTY_DRIVER_KEY = "driverName";
    private static final Lock INSTANCE_LOCK = new ReentrantLock();
    private static final Lock CONNECTION_LOCK = new ReentrantLock();
    private static final Condition CONNECTION_CONDITION = CONNECTION_LOCK.newCondition();
    private static NNSConnectionPool instance;
    private ArrayDeque<Connection> freeConnections;
    private ArrayDeque<Connection> busyConnections;
    private PropertiesReader propertiesReader;

    private NNSConnectionPool() {
        propertiesReader = new NNSProdPropertiesReader();
        freeConnections = new ArrayDeque<>();
        busyConnections = new ArrayDeque<>();
    }

    public void setPropertiesReader(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
    }

    public int getFreeConnectionsSize() {
        return freeConnections.size();
    }

    public int getBusyConnectionsSize() {
        return busyConnections.size();
    }

    public static NNSConnectionPool getInstance() {
        if (!INSTANCE_CREATED.get()) {
            try {
                INSTANCE_LOCK.lock();
                if (instance == null) {
                    instance = new NNSConnectionPool();
                    INSTANCE_CREATED.set(true);
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return instance;
    }

    public void initializeConnectionPool(int connectionsNumber) throws NNSConnectionPoolException {
        try {
            closeConnections();
            CONNECTION_LOCK.lock();
            Class.forName(propertiesReader.readProperties().getProperty(PROPERTY_DRIVER_KEY));

            for (int i = 0; i < connectionsNumber; i++) {
                freeConnections.push(new NNSProxyConnection(DriverManager.getConnection(propertiesReader.readUrl(), propertiesReader.readProperties())));
            }
        } catch (ClassNotFoundException | SQLException | NNSUtilException e) {
            throw new NNSConnectionPoolException(e);
        } finally {
            CONNECTION_LOCK.unlock();
        }
    }

    public Connection acquireConnection() throws NNSConnectionPoolException {
        try {
            CONNECTION_LOCK.lock();
            if (!freeConnections.isEmpty() || !busyConnections.isEmpty()) {
                if (freeConnections.isEmpty()) {
                    CONNECTION_CONDITION.await();
                }
                Connection connection = freeConnections.poll();
                busyConnections.push(connection);
                return connection;
            } else {
                throw new NNSConnectionPoolException();
            }
        } catch (InterruptedException e) {
            throw new NNSConnectionPoolException(e);
        } finally {
            CONNECTION_LOCK.unlock();
        }
    }

    public void putBackConnection(Connection connection) throws NNSConnectionPoolException {
        if (connection == null) {
            throw new NNSConnectionPoolException();
        }

        try {
            CONNECTION_LOCK.lock();
            if (busyConnections.remove(connection)) {
                freeConnections.add(connection);
                if (freeConnections.size() >= 1) {
                    CONNECTION_CONDITION.signal();
                }
            } else {
                throw new NNSConnectionPoolException();
            }
        } finally {
            CONNECTION_LOCK.unlock();
        }
    }

    public void closeConnections() throws NNSConnectionPoolException {
        try {
            CONNECTION_LOCK.lock();
            TimeUnit.SECONDS.sleep(1);
            for (Connection connection : freeConnections) {
                NNSProxyConnection NNSProxyConnection = (NNSProxyConnection) connection;
                NNSProxyConnection.getConnection().close();

                freeConnections = new ArrayDeque<>();
                busyConnections = new ArrayDeque<>();
            }
        } catch (SQLException | InterruptedException e) {
            throw new NNSConnectionPoolException(e);
        } finally {
            CONNECTION_LOCK.unlock();
        }
    }
}

