package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.ConnectionStoreException;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.dao.mapper.DaoMapper;
import com.lashkevich.stores.util.provider.ConnectionProvider;
import com.lashkevich.stores.util.provider.impl.ConnectionProviderImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoutryDaoImpl implements CountryDao {
    private static final String ADD_COUNTRY_SQL = "INSERT INTO countries (id, name) VALUES (?, ?);";
    private static final String FIND_ALL_COUNTRIES_SQL = "SELECT countries.id AS country_id, countries.name AS country_name FROM countries;";
    private static final String FIND_COUNTRY_BY_ID_SQL = "SELECT countries.id AS country_id, countries.name AS country_name FROM countries WHERE id = ?;";
    private static final String DELETE_COUNTRY_BY_ID_SQL = "DELETE FROM countries WHERE id = ?;";
    private static final String UPDATE_COUNTRY_SQL = "UPDATE countries SET name = ? WHERE id = ?;";

    private ConnectionProvider connectionProvider;

    public CoutryDaoImpl() {
        connectionProvider = new ConnectionProviderImpl();
    }

    public void setConnectionProvider(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public boolean add(Country country) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_COUNTRY_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            preparedStatement.setLong(1, country.getId());
            preparedStatement.setString(2, country.getName());
            return preparedStatement.executeUpdate() == 1;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public List<Country> findAll() throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement()) {
            List<Country> countries = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_COUNTRIES_SQL);

            while (resultSet.next()) {
                countries.add(DaoMapper.mapCountry(resultSet));
            }

            return countries;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public Country findById(long id) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COUNTRY_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Country country = new Country();

            while (resultSet.next()) {
                country = DaoMapper.mapCountry(resultSet);
            }

            return country;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public boolean update(Country country) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COUNTRY_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            preparedStatement.setString(1, country.getName());
            preparedStatement.setLong(2, country.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public boolean remove(long id) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COUNTRY_BY_ID_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }
}
