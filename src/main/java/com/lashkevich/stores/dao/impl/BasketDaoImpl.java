package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.BasketDao;
import com.lashkevich.stores.dao.mapper.DaoMapper;
import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.ConnectionStoreException;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.provider.ConnectionProvider;
import com.lashkevich.stores.util.provider.impl.ConnectionProviderImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasketDaoImpl implements BasketDao {
    private static final String ADD_TO_BASKET_SQL = "INSERT INTO baskets (good_id, user_id, quantity) VALUES (?, ?, ?);";
    private static final String FIND_ALL_USERS_AND_THEIR_GOODS_SQL = "SELECT baskets.user_id AS primary_key_good_id, baskets.user_id," +
            " baskets.quantity, goods.id AS good_id, goods.name AS good_name, goods.summary AS good_summary, goods.description AS good_description FROM baskets LEFT JOIN goods ON baskets.good_id = goods.id ORDER BY baskets.good_id;";
    private static final String FIND_GOODS_BY_USER_SQL = "SELECT baskets.user_id AS primary_key_good_id, baskets.user_id," +
            " baskets.quantity, goods.id AS good_id, goods.name AS good_name, goods.summary AS good_summary, goods.description AS good_description FROM baskets LEFT JOIN goods ON baskets.good_id = goods.id WHERE user_id = ?;";
    private static final String DELETE_BASKET_SQL = "DELETE FROM baskets WHERE user_id = ?;";

    private ConnectionProvider connectionProvider;

    public BasketDaoImpl() {
        connectionProvider = new ConnectionProviderImpl();
    }

    public void setConnectionProvider(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public boolean add(Basket basket, long userId) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_BASKET_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            int changeLineCounter = 0;

            for (Map.Entry<Good, Integer> goods : basket.getGoods().entrySet()) {
                preparedStatement.setLong(1, goods.getKey().getId());
                preparedStatement.setLong(2, userId);
                preparedStatement.setLong(3, goods.getValue());
                changeLineCounter += preparedStatement.executeUpdate();
            }

            return changeLineCounter >= 1;
        } catch (ConnectionStoreException |
                SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public List<Basket> findAll() throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement()) {
            List<Basket> baskets = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_AND_THEIR_GOODS_SQL);

            while (resultSet.next()) {
                baskets.add(DaoMapper.mapBasket(resultSet));
            }

            return baskets;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public List<Basket> findByUser(long id) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_GOODS_BY_USER_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List <Basket> baskets = new ArrayList<>();

            while (resultSet.next()) {
                baskets.add(DaoMapper.mapBasket(resultSet));
            }

            return baskets;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public boolean update(Basket basket, long userId) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement deletePreparedStatement = connection.prepareStatement(DELETE_BASKET_SQL);
             PreparedStatement addPreparedStatement = connection.prepareStatement(ADD_TO_BASKET_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            deletePreparedStatement.setLong(1, userId);
            deletePreparedStatement.executeUpdate();
            int changeLineCounter = 0;

            for (Map.Entry<Good, Integer> goods : basket.getGoods().entrySet()) {
                addPreparedStatement.setLong(1, goods.getKey().getId());
                addPreparedStatement.setLong(2, userId);
                addPreparedStatement.setLong(3, goods.getValue());
                changeLineCounter += addPreparedStatement.executeUpdate();
            }

            return changeLineCounter >= 1;
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public boolean remove(long userId) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BASKET_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            preparedStatement.setLong(1, userId);
            return preparedStatement.executeUpdate() >= 1;
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }
}
