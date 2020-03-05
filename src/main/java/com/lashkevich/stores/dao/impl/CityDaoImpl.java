package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.exception.ConnectionStoreException;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.dao.mapper.DaoMapper;
import com.lashkevich.stores.util.connection.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoImpl implements CityDao {
    private static final String ADD_CITY_SQL = "INSERT INTO cities (id, name, country_id) VALUES (?, ?, ?);";
    private static final String FIND_ALL_CITIES_SQL = "SELECT id, name, country_id FROM cities;";
    private static final String FIND_CITY_BY_ID_SQL = "SELECT id, name, country_id FROM cities WHERE id = ?;";
    private static final String UPDATE_CITY_SQL = "UPDATE cities SET name = ?, country_id = ? WHERE id = ?;";
    private static final String DELETE_CITY_BY_ID_SQL = "DELETE FROM cities WHERE id = ?;";


    @Override
    public void add(City city) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CITY_SQL)) {
            preparedStatement.setLong(1, city.getId());
            preparedStatement.setString(2, city.getName());
            preparedStatement.setLong(3, city.getCountry());
            preparedStatement.executeUpdate();
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public List<City> getAll() throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            List<City> cities = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_CITIES_SQL);

            while (resultSet.next()) {
                cities.add(DaoMapper.mapCity(resultSet));
            }

            return cities;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public City getById(long id) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_CITY_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            City city = new City();

            while (resultSet.next()) {
                city = DaoMapper.mapCity(resultSet);
            }

            return city;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public void update(City city) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CITY_SQL)) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setLong(2, city.getCountry());
            preparedStatement.setLong(3, city.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public void remove(long id) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CITY_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }
}
