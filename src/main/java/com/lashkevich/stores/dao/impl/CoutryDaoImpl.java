package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.ConnectionStoreException;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.dao.mapper.DaoMapper;
import com.lashkevich.stores.util.connection.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoutryDaoImpl implements CountryDao {
    private static final String ADD_COUNTRY_SQL = "INSERT INTO countries (id, name) VALUES (?, ?);";
    private static final String FIND_ALL_COUNTRIES_SQL = "SELECT id, name FROM countries;";
    private static final String FIND_COUNTRY_BY_ID_SQL = "SELECT id, name FROM countries WHERE id = ?;";
    private static final String DELETE_COUNTRY_BY_ID_SQL = "DELETE FROM countries WHERE id = ?;";
    private static final String UPDATE_COUNTRY_SQL = "UPDATE countries SET name = ? WHERE id = ?;";

    @Override
    public void add(Country country) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_COUNTRY_SQL)) {
            preparedStatement.setLong(1, country.getId());
            preparedStatement.setString(2, country.getName());
            preparedStatement.executeUpdate();
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public List<Country> getAll() throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
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
    public Country getById(long id) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
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
    public void update(Country country) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COUNTRY_SQL)) {
            preparedStatement.setString(1, country.getName());
            preparedStatement.setLong(2, country.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public void remove(long id) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COUNTRY_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }
}
