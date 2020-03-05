package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.GoodPriceDao;
import com.lashkevich.stores.dao.mapper.DaoMapper;
import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.ConnectionStoreException;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.connection.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodPriceDaoImpl implements GoodPriceDao {
    private static final String ADD_TO_GOOD_PRICE_SQL = "INSERT INTO good_prices (country_id, good_id, price) VALUES (?, ?, ?);";
    private static final String FIND_ALL_PRICES_SQL = "SELECT country_id, good_id, price FROM good_prices;";
    private static final String FIND_PRICE_BY_COUNTRY_AND_GOOD_SQL = "SELECT country_id, good_id, price FROM good_prices WHERE country_id = ? AND good_id = ?";
    private static final String UPDATE_GOOD_PRICE_SQL = "UPDATE good_prices SET country_id = ?, good_id = ?, price = ? WHERE country_id = ? AND good_id = ?;";
    private static final String DELETE_PRICE_BY_COUNTRY_AND_GOOD_SQL = "DELETE FROM baskets WHERE country_id = ? AND good_id = ?;";

    @Override
    public void add(GoodPrice goodPrice) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_GOOD_PRICE_SQL)) {
            preparedStatement.setLong(1, goodPrice.getCountry());
            preparedStatement.setLong(2, goodPrice.getGood());
            preparedStatement.setDouble(2, goodPrice.getPrice());
            preparedStatement.executeUpdate();
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public List<GoodPrice> getAll() throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            List<GoodPrice> goodPrices = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_PRICES_SQL);

            while (resultSet.next()) {
                goodPrices.add(DaoMapper.mapGoodPrice(resultSet));
            }

            return goodPrices;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public GoodPrice getByCountryAndGood(long countryId, long goodId) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRICE_BY_COUNTRY_AND_GOOD_SQL)) {
            preparedStatement.setLong(1, countryId);
            preparedStatement.setLong(2, goodId);
            ResultSet resultSet = preparedStatement.executeQuery();
            GoodPrice goodPrice = new GoodPrice();

            while (resultSet.next()) {
                goodPrice = DaoMapper.mapGoodPrice(resultSet);
            }

            return goodPrice;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public void update(long countryId, long goodId, GoodPrice goodPrice) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GOOD_PRICE_SQL)) {
            preparedStatement.setLong(1, goodPrice.getCountry());
            preparedStatement.setLong(2, goodPrice.getGood());
            preparedStatement.setDouble(3, goodPrice.getPrice());
            preparedStatement.setLong(4, countryId);
            preparedStatement.setLong(5, goodId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public void remove(long countryId, long goodId) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRICE_BY_COUNTRY_AND_GOOD_SQL)) {
            preparedStatement.setLong(1, goodId);
            preparedStatement.setLong(2, countryId);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }
}
