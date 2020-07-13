package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.BasketDao;
import com.lashkevich.stores.dao.mapper.NNSDaoMapper;
import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.NNSConnectionPoolException;
import com.lashkevich.stores.exception.NNSUtilException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.pool.NNSConnectionPool;
import com.lashkevich.stores.util.reader.PropertiesReader;
import com.lashkevich.stores.util.reader.impl.NNSProdPropertiesReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NNSBasketDao implements BasketDao {
    private static final String ADD_TO_BASKET_SQL = "INSERT INTO baskets (good_id, user_id, quantity) VALUES (?, ?, ?);";
    private static final String FIND_ALL_USERS_AND_THEIR_GOODS_SQL = "SELECT baskets.user_id AS primary_key_good_id, baskets.user_id," +
            " baskets.quantity, goods.id AS good_id, goods.name AS good_name, goods.summary AS good_summary, goods.description AS good_description FROM baskets LEFT JOIN goods ON baskets.good_id = goods.id ORDER BY baskets.good_id;";
    private static final String FIND_GOODS_BY_USER_SQL = "SELECT baskets.user_id AS primary_key_good_id, baskets.user_id," +
            " baskets.quantity, goods.id AS good_id, goods.name AS good_name, goods.summary AS good_summary, goods.description AS good_description FROM baskets LEFT JOIN goods ON baskets.good_id = goods.id WHERE user_id = ?;";
    private static final String DELETE_BASKET_SQL = "DELETE FROM baskets WHERE user_id = ?;";

    private PropertiesReader propertiesReader;

    public NNSBasketDao() {
        propertiesReader = new NNSProdPropertiesReader();
    }

    public void setPropertiesReader(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
    }

    @Override
    public boolean add(Basket basket, long userId) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_BASKET_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            return addBasket(preparedStatement, basket, userId);
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public List<Basket> findAll() throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            List<Basket> baskets = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_AND_THEIR_GOODS_SQL);

            while (resultSet.next()) {
                baskets.add(NNSDaoMapper.mapAllBaskets(resultSet));
            }

            return baskets;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public Optional<Basket> findByUser(long id) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_GOODS_BY_USER_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Basket basket = (NNSDaoMapper.mapUserBasket(resultSet));
            Optional<Basket> basketOptional = Optional.empty();

            if (basket.getGoods().size() != 0) {
                basketOptional = Optional.of(basket);
            }
            return basketOptional;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public boolean update(Basket basket, long userId) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement deletePreparedStatement = connection.prepareStatement(DELETE_BASKET_SQL);
             PreparedStatement addPreparedStatement = connection.prepareStatement(ADD_TO_BASKET_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            deletePreparedStatement.setLong(1, userId);
            deletePreparedStatement.executeUpdate();
            return addBasket(addPreparedStatement, basket, userId);
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public boolean remove(long userId) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BASKET_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setLong(1, userId);
            return preparedStatement.executeUpdate() >= 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    private boolean addBasket(PreparedStatement statement, Basket basket, long userId) throws SQLException {
        int changeLineCounter = 0;

        for (Map.Entry<Good, Integer> goods : basket.getGoods().entrySet()) {
            statement.setLong(1, goods.getKey().getId());
            statement.setLong(2, userId);
            statement.setLong(3, goods.getValue());
            changeLineCounter += statement.executeUpdate();
        }

        return changeLineCounter >= 1;
    }
}
