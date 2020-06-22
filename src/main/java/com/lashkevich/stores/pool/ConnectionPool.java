package com.lashkevich.stores.pool;

import com.lashkevich.stores.constant.PoolConstant;
import com.lashkevich.stores.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static Lock instanceLock = new ReentrantLock();
    private static ConnectionPool instance;
    private Lock connectionLock;
    private Condition conditionCondition;
    private ArrayDeque<Connection> freeConnections;
    private ArrayDeque<Connection> busyConnections;

    private ConnectionPool() {
        freeConnections = new ArrayDeque<>();
        busyConnections = new ArrayDeque<>();
        connectionLock = new ReentrantLock();
        conditionCondition = connectionLock.newCondition();
    }

    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            try {
                instanceLock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }

        return instance;
    }

    public int getFreeConnectionsSize() {
        return freeConnections.size();
    }

    public int getBusyConnectionsSize() {
        return busyConnections.size();
    }

    public void initializeConnectionPool(int connectionsNumber, String url, String user, String password) throws ConnectionPoolException {
        try {
            connectionLock.lock();
            closeConnections();
            Class.forName(PoolConstant.DRIVER_NAME);

            for (int i = 0; i < connectionsNumber; i++) {
                freeConnections.push(new ProxyConnection(DriverManager.getConnection(url, user, password)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionPoolException(e);
        } finally {
            connectionLock.unlock();
        }
    }

    public Connection acquireConnection() throws ConnectionPoolException {
        try {
            connectionLock.lock();
            if (!freeConnections.isEmpty() || !busyConnections.isEmpty()) {
                if (freeConnections.isEmpty()) {
                    conditionCondition.await();
                }
                Connection connection = freeConnections.poll();
                busyConnections.push(connection);
                return connection;
            } else {
                throw new ConnectionPoolException();
            }
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e);
        } finally {
            connectionLock.unlock();
        }
    }

    public void putBackConnection(Connection connection) throws ConnectionPoolException {
        if (connection == null) {
            throw new ConnectionPoolException();
        }

        try {
            connectionLock.lock();
            if (busyConnections.remove(connection)) {
                freeConnections.add(connection);
                if (freeConnections.size() >= 1) {
                    conditionCondition.signal();
                }
            } else {
                throw new ConnectionPoolException();
            }
        } finally {
            connectionLock.unlock();
        }
    }

    public void closeConnections() throws ConnectionPoolException {
        try {
            connectionLock.lock();
            TimeUnit.SECONDS.sleep(1);
            for (Connection connection : freeConnections) {
                ProxyConnection proxyConnection = (ProxyConnection) connection;
                proxyConnection.getConnection().close();

                freeConnections = new ArrayDeque<>();
                busyConnections = new ArrayDeque<>();
            }
        } catch (SQLException | InterruptedException e) {
            throw new ConnectionPoolException(e);
        } finally {
            connectionLock.unlock();
        }
    }
}

