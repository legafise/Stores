package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.dao.mapper.NNSDaoMapper;
import com.lashkevich.stores.entity.City;
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

public class NNSCityDao implements CityDao {
    private static final String ADD_CITY_SQL = "INSERT INTO cities (id, name, country_id) VALUES (?, ?, ?);";
    private static final String FIND_ALL_CITIES_SQL = "SELECT cities.id AS city_id, cities.name AS city_name, cities.country_id AS primary_key_country_id, countries.id AS" +
            " country_id, countries.name AS country_name, countries.currency_id AS currency_id, currencies.name AS currency_name, currencies.coefficient AS" +
            " currency_coefficient, currencies.symbol AS currency_symbol FROM cities INNER JOIN countries ON cities.country_id = countries.id INNER JOIN currencies ON" +
            " countries.currency_id = currencies.id ORDER BY cities.id;";
    private static final String FIND_CITY_BY_ID_SQL = "SELECT cities.id AS city_id, cities.name AS city_name, cities.country_id AS" +
            " primary_key_country_id, countries.id AS country_id, countries.name AS country_name, countries.currency_id AS currency_id, currencies.name AS" +
            " currency_name, currencies.coefficient AS currency_coefficient, currencies.symbol AS currency_symbol FROM cities INNER JOIN countries ON" +
            " cities.country_id = countries.id INNER JOIN currencies ON countries.currency_id = currencies.id WHERE cities.id = ?;";
    private static final String UPDATE_CITY_SQL = "UPDATE cities SET name = ?, country_id = ? WHERE id = ?;";
    private static final String DELETE_CITY_BY_ID_SQL = "DELETE FROM cities WHERE id = ?;";

    private PropertiesReader propertiesReader;

    public NNSCityDao() {
        propertiesReader = new NNSProdPropertiesReader();
    }

    @Override
    public void setPropertiesReader(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
    }

    @Override
    public boolean add(City city) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CITY_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setLong(1, city.getId());
            preparedStatement.setString(2, city.getName());
            preparedStatement.setLong(3, city.getCountry().getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public List<City> findAll() throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            List<City> cities = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_CITIES_SQL);

            while (resultSet.next()) {
                cities.add(NNSDaoMapper.mapCity(resultSet));
            }

            return cities;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public Optional<City> findById(long id) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_CITY_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            City city = new City();

            while (resultSet.next()) {
                city = NNSDaoMapper.mapCity(resultSet);
            }

            Optional<City> cityOptional = Optional.empty();
            if (city.getId() != 0) {
                cityOptional = Optional.of(city);
            }

            return cityOptional;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public boolean update(City city) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CITY_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setString(1, city.getName());
            preparedStatement.setLong(2, city.getCountry().getId());
            preparedStatement.setLong(3, city.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public boolean remove(long id) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CITY_BY_ID_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }
}
