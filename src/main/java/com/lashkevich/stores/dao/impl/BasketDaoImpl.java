package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.BasketDao;
import com.lashkevich.stores.dao.mapper.DaoMapper;
import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.ConnectionStoreException;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.connection.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasketDaoImpl implements BasketDao {
    private static final String ADD_TO_BASKET_SQL = "INSERT INTO baskets (good_id, user_id, quantity) VALUES (?, ?, ?);";
    private static final String FIND_ALL_USERS_AND_THEIR_GOODS_SQL = "SELECT baskets.user_id AS primary_key_good_id, baskets.user_id," +
            " baskets.quantity, goods.id AS good_id, goods.name AS good_name, goods.summary, goods.description FROM baskets LEFT JOIN goods ON baskets.good_id = goods.id;";
    private static final String FIND_GOODS_BY_USER_SQL = "SELECT baskets.user_id AS primary_key_good_id, baskets.user_id," +
            " baskets.quantity, goods.id AS good_id, goods.name AS good_name, goods.summary, goods.description FROM baskets LEFT JOIN goods ON baskets.good_id = goods.id WHERE user_id = ?;";
    private static final String DELETE_BASKET_SQL = "DELETE FROM baskets WHERE user_id = ?;";

    @Override
    public void add(Basket basket, long userId) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_BASKET_SQL)) {
            
            for (Map.Entry<Good, Integer> goods : basket.getGoods().entrySet()) {
                preparedStatement.setLong(1, goods.getKey().getId());
                preparedStatement.setLong(2, userId);
                preparedStatement.setLong(3, goods.getValue());
                preparedStatement.executeUpdate();
            }
            
        } catch (ConnectionStoreException |
                SQLException e) {
            throw new DaoStoreException(e);
        }

    }

    @Override
    public List<Basket> findAll() throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
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
    public Basket findByUser(long id) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_GOODS_BY_USER_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Basket basket = new Basket();

            while (resultSet.next()) {
                basket = DaoMapper.mapBasket(resultSet);
            }

            return basket;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public void update(Basket basket, long userId) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement deletePreparedStatement = connection.prepareStatement(DELETE_BASKET_SQL);
             PreparedStatement addPreparedStatement = connection.prepareStatement(ADD_TO_BASKET_SQL)) {
            deletePreparedStatement.setLong(1, userId);
            deletePreparedStatement.executeUpdate();

            for (Map.Entry<Good, Integer> goods : basket.getGoods().entrySet()) {
                addPreparedStatement.setLong(1, goods.getKey().getId());
                addPreparedStatement.setLong(2, userId);
                addPreparedStatement.setLong(3, goods.getValue());
                addPreparedStatement.executeUpdate();
            }

        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public void remove(long userId) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BASKET_SQL)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }
}
