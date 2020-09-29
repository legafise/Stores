package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.CurrencyDao;
import com.lashkevich.stores.dao.mapper.NNSDaoMapper;
import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.exception.NNSConnectionPoolException;
import com.lashkevich.stores.exception.NNSUtilException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.pool.NNSConnectionPool;
import com.lashkevich.stores.util.reader.PropertiesReader;
import com.lashkevich.stores.util.reader.impl.NNSProdPropertiesReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NNSCurrencyDao implements CurrencyDao {
    private static final String ADD_CURRENCY_SQL = "INSERT INTO currencies (id, name, coefficient, symbol) VALUES (?, ?, ?, ?);";
    private static final String FIND_ALL_CURRENCY_SQL = "SELECT currencies.id AS currency_id, currencies.name AS currency_name, currencies.coefficient AS currency_coefficient," +
            " currencies.symbol AS currency_symbol FROM currencies ORDER BY currencies.id;";
    private static final String FIND_CURRENCY_BY_ID_SQL = "SELECT currencies.id AS currency_id, currencies.name AS currency_name," +
            " currencies.coefficient AS currency_coefficient, currencies.symbol AS currency_symbol FROM currencies WHERE currencies.id = ?";
    private static final String UPDATE_CURRENCY_SQL = "UPDATE currencies SET name = ?, coefficient = ?, symbol = ? WHERE id = ?;";
    private static final String DELETE_CURRENCY_BY_ID_SQL = "DELETE FROM currencies WHERE id = ?;";

    private PropertiesReader propertiesReader;

    public NNSCurrencyDao() {
        propertiesReader = new NNSProdPropertiesReader();
    }

    @Override
    public void setPropertiesReader(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
    }

    @Override
    public boolean add(Currency currency) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CURRENCY_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setLong(1, currency.getId());
            preparedStatement.setString(2, currency.getName());
            preparedStatement.setBigDecimal(3, currency.getCoefficient());
            preparedStatement.setString(4, currency.getSymbol());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public List<Currency> findAll() throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_CURRENCY_SQL);
            List<Currency> currencies = new ArrayList<>();

            while (resultSet.next()) {
                currencies.add(NNSDaoMapper.mapCurrency(resultSet));
            }

            return currencies;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public Optional<Currency> findById(long id) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_CURRENCY_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Currency currency = new Currency();

            while (resultSet.next()) {
                currency = NNSDaoMapper.mapCurrency(resultSet);
            }

            Optional<Currency> currencyOptional = Optional.empty();
            if (currency.getId() != 0) {
                currencyOptional = Optional.of(currency);
            }

            return currencyOptional;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public boolean update(Currency currency) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CURRENCY_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setString(1, currency.getName());
            preparedStatement.setBigDecimal(2, currency.getCoefficient());
            preparedStatement.setString(3, currency.getSymbol());
            preparedStatement.setLong(4, currency.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public boolean remove(long id) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CURRENCY_BY_ID_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }
}
