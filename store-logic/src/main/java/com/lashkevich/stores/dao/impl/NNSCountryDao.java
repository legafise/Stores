package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.dao.mapper.NNSDaoMapper;
import com.lashkevich.stores.entity.Country;
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

public class NNSCountryDao implements CountryDao {
    private static final String ADD_COUNTRY_SQL = "INSERT INTO countries (id, name) VALUES (?, ?);";
    private static final String FIND_ALL_COUNTRIES_SQL = "SELECT countries.id AS country_id, countries.name AS country_name FROM countries;";
    private static final String FIND_COUNTRY_BY_ID_SQL = "SELECT countries.id AS country_id, countries.name AS country_name FROM countries WHERE id = ?;";
    private static final String DELETE_COUNTRY_BY_ID_SQL = "DELETE FROM countries WHERE id = ?;";
    private static final String UPDATE_COUNTRY_SQL = "UPDATE countries SET name = ? WHERE id = ?;";

    private PropertiesReader propertiesReader;

    public NNSCountryDao() {
        propertiesReader = new NNSProdPropertiesReader();
    }

    @Override
    public void setPropertiesReader(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
    }

    @Override
    public boolean add(Country country) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_COUNTRY_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setLong(1, country.getId());
            preparedStatement.setString(2, country.getName());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public List<Country> findAll() throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            List<Country> countries = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_COUNTRIES_SQL);

            while (resultSet.next()) {
                countries.add(NNSDaoMapper.mapCountry(resultSet));
            }

            return countries;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public Optional<Country> findById(long id) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COUNTRY_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Country country = new Country();

            while (resultSet.next()) {
                country = NNSDaoMapper.mapCountry(resultSet);
            }

            Optional<Country> countryOptional = Optional.empty();
            if (country.getId() != 0) {
                countryOptional = Optional.of(country);
            }

            return countryOptional;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public boolean update(Country country) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COUNTRY_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setString(1, country.getName());
            preparedStatement.setLong(2, country.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public boolean remove(long id) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COUNTRY_BY_ID_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }
}
