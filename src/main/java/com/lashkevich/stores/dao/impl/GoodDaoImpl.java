package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.ConnectionStoreException;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.dao.mapper.DaoMapper;
import com.lashkevich.stores.util.provider.ConnectionProvider;
import com.lashkevich.stores.util.provider.impl.ConnectionProviderImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodDaoImpl implements GoodDao {
    private static final String ADD_GOOD_SQL = "INSERT INTO goods (id, name, summary, description) VALUES (?, ?, ?, ?);";
    private static final String FIND_ALL_GOODS_SQL = "SELECT goods.id AS good_id, goods.name AS good_name, goods.summary AS" +
            " good_summary, goods.description AS good_description FROM goods;";
    private static final String FIND_GOOD_BY_ID_SQL = "SELECT goods.id AS good_id, goods.name AS good_name, goods.summary AS" +
            " good_summary, goods.description AS good_description FROM goods WHERE id = ?;";
    private static final String UPDATE_GOOD_SQL = "UPDATE goods SET name = ?, summary = ?, description = ? WHERE id = ?;";
    private static final String DELETE_GOOD_BY_ID_SQL = "DELETE FROM goods WHERE id = ?;";

    private ConnectionProvider connectionProvider;

    public GoodDaoImpl() {
        connectionProvider = new ConnectionProviderImpl();
    }

    public void setConnectionProvider(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public boolean add(Good good) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_GOOD_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            preparedStatement.setLong(1, good.getId());
            preparedStatement.setString(2, good.getName());
            preparedStatement.setString(3, good.getSummary());
            preparedStatement.setString(4, good.getDescription());
            return preparedStatement.executeUpdate() == 1;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public List<Good> findAll() throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement()) {
            List<Good> goods = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_GOODS_SQL);

            while (resultSet.next()) {
                goods.add(DaoMapper.mapGood(resultSet));
            }

            return goods;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public Good findById(long id) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_GOOD_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Good good = new Good();

            while (resultSet.next()) {
                good = DaoMapper.mapGood(resultSet);
            }

            return good;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public boolean update(Good good) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GOOD_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            preparedStatement.setString(1, good.getName());
            preparedStatement.setString(2, good.getSummary());
            preparedStatement.setString(3, good.getDescription());
            preparedStatement.setLong(4, good.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }

    }

    @Override
    public boolean remove(long id) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GOOD_BY_ID_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }
}
