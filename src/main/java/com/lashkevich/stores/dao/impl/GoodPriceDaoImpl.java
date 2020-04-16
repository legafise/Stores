package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.GoodPriceDao;
import com.lashkevich.stores.dao.mapper.DaoMapper;
import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.ConnectionStoreException;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.provider.ConnectionProvider;
import com.lashkevich.stores.util.provider.impl.ConnectionProviderImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodPriceDaoImpl implements GoodPriceDao {
    private static final String ADD_TO_GOOD_PRICE_SQL = "INSERT INTO good_prices (country_id, good_id, price) VALUES (?, ?, ?);";
    private static final String FIND_ALL_PRICES_SQL = "SELECT good_prices.country_id AS primary_key_country_id, good_prices.good_id" +
            " AS primary_key_good_id, good_prices.price, countries.id AS country_id, countries.name AS country_name, goods.id AS" +
            " good_id, goods.name AS good_name, goods.summary AS good_summary, goods.description AS good_description FROM " +
            "good_prices INNER JOIN countries ON good_prices.country_id = countries.id INNER JOIN goods ON good_prices.good_id = goods.id;";
    private static final String FIND_PRICE_BY_COUNTRY_AND_GOOD_SQL = "SELECT good_prices.country_id AS primary_key_country_id, good_prices.good_id" +
            " AS primary_key_good_id, good_prices.price, countries.id AS country_id, countries.name AS country_name, goods.id AS" +
            " good_id, goods.name AS good_name, goods.summary AS good_summary, goods.description AS good_description FROM " +
            "good_prices INNER JOIN countries ON good_prices.country_id = countries.id INNER JOIN goods ON good_prices.good_id = goods.id WHERE " +
            "good_prices.country_id = ? AND good_prices.good_id = ?";
    private static final String UPDATE_GOOD_PRICE_SQL = "UPDATE good_prices SET country_id = ?, good_id = ?, price = ? WHERE country_id = ? AND good_id = ?;";
    private static final String DELETE_PRICE_BY_COUNTRY_AND_GOOD_SQL = "DELETE FROM good_prices WHERE country_id = ? AND good_id = ?;";

    private ConnectionProvider connectionProvider;

    public GoodPriceDaoImpl() {
        connectionProvider = new ConnectionProviderImpl();
    }

    @Override
    public void setConnectionProvider(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public boolean add(GoodPrice goodPrice) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TO_GOOD_PRICE_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            preparedStatement.setLong(1, goodPrice.getCountry().getId());
            preparedStatement.setLong(2, goodPrice.getGood().getId());
            preparedStatement.setBigDecimal(3, goodPrice.getPrice());
            return preparedStatement.executeUpdate() == 1;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public List<GoodPrice> findAll() throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
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
    public GoodPrice findByCountryAndGood(long countryId, long goodId) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
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
    public boolean update(long countryId, long goodId, GoodPrice goodPrice) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GOOD_PRICE_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            preparedStatement.setLong(1, goodPrice.getCountry().getId());
            preparedStatement.setLong(2, goodPrice.getGood().getId());
            preparedStatement.setBigDecimal(3, goodPrice.getPrice());
            preparedStatement.setLong(4, countryId);
            preparedStatement.setLong(5, goodId);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public boolean remove(long countryId, long goodId) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRICE_BY_COUNTRY_AND_GOOD_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            preparedStatement.setLong(1, countryId);
            preparedStatement.setLong(2, goodId);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }
}
