package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.BasketDao;
import com.lashkevich.stores.dao.mapper.DaoMapper;
import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.exception.ConnectionStoreException;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.connection.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasketDaoImpl implements BasketDao {
    private static final String ADD_TO_BASKET_SQL = "INSERT INTO baskets (good_id, user_id) VALUES (?, ?);";
    private static final String FIND_ALL_USERS_AND_THEIR_GOODS_SQL = "SELECT good_id, user_id FROM baskets;";
    private static final String FIND_GOODS_BY_USER_SQL = "SELECT good_id, user_id FROM baskets WHERE user_id = ?;";
    private static final String UPDATE_BASKET_SQL = "UPDATE baskets SET good_id = ?, user_id = ? WHERE good_id = ? AND user_id = ?;";
    private static final String DELETE_USER_AND_THEY_GOODS_BY_THEIR_ID_SQL = "DELETE FROM baskets WHERE good_id = ? AND user_id = ?;";

    @Override
    public void add(Basket basket) throws DaoStoreException {
        try(Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_BASKET_SQL)) {
            preparedStatement.setLong(1, basket.getGood());
            preparedStatement.setLong(2, basket.getUser());
            preparedStatement.executeUpdate();
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public List<Basket> getAll() throws DaoStoreException {
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
    public Basket getByUser(long id) throws DaoStoreException {
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
    public void update(long goodId, long userId, Basket basket) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BASKET_SQL)) {
            preparedStatement.setLong(1, basket.getGood());
            preparedStatement.setLong(2, basket.getUser());
            preparedStatement.setLong(3, goodId);
            preparedStatement.setLong(4, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public void remove(long goodId, long userId) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_AND_THEY_GOODS_BY_THEIR_ID_SQL)) {
            preparedStatement.setLong(1, goodId);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }
}
