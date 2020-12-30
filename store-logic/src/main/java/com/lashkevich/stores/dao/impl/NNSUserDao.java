package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.dao.mapper.NNSDaoMapper;
import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.NNSConnectionPoolException;
import com.lashkevich.stores.exception.NNSUtilException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.pool.NNSConnectionPool;
import com.lashkevich.stores.util.converter.NNSDateConverter;
import com.lashkevich.stores.util.reader.PropertiesReader;
import com.lashkevich.stores.util.reader.impl.NNSProdPropertiesReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NNSUserDao implements UserDao {
    private static final String ADD_USER_SQL = "INSERT INTO users (name, surname, login, password, email, " +
            "birth_date, role_id, city_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String FIND_ALL_USERS_SQL = "SELECT users.id AS user_id, users.name AS user_name, users.surname, users.login, users.password," +
            " users.email, users.birth_date, users.role_id AS primary_key_role_id, users.city_id AS primary_key_city_id," +
            " roles.id AS role_id, roles.name AS role_name, cities.id AS city_id, cities.name AS city_name, cities.country_id AS" +
            " primary_key_country_id, countries.id AS country_id, countries.name AS country_name, countries.currency_id AS currency_id, baskets.good_id AS primary_key_good_id," +
            " baskets.user_id, baskets.quantity, goods.id AS good_id, goods.name AS good_name, goods.price AS good_price ,goods.summary AS good_summary, goods.description AS good_description, goods.img AS good_img, currencies.name AS" +
            " currency_name, currencies.coefficient AS currency_coefficient, currencies.symbol AS currency_symbol FROM users" +
            " LEFT JOIN roles ON users.role_id = roles.id LEFT JOIN cities ON users.city_id = cities.id LEFT JOIN countries ON" +
            " cities.country_id = countries.id LEFT JOIN baskets ON users.id = baskets.user_id LEFT JOIN goods ON baskets.good_id = goods.id LEFT JOIN currencies ON countries.currency_id = currencies.id ORDER BY users.id;";
    private static final String FIND_USER_BY_ID_SQL = "SELECT users.id AS user_id, users.name AS user_name, users.surname, users.login, users.password," +
            " users.email, users.birth_date, users.role_id AS primary_key_role_id, users.city_id AS primary_key_city_id," +
            " roles.id AS role_id, roles.name AS role_name, cities.id AS city_id, cities.name AS city_name, cities.country_id AS" +
            " primary_key_country_id, countries.id AS country_id, countries.name AS country_name, baskets.good_id AS primary_key_good_id," +
            " baskets.user_id, baskets.quantity, goods.id AS good_id, goods.name AS good_name, goods.price AS good_price, goods.summary AS good_summary, goods.description AS good_description, goods.img AS good_img, countries.currency_id AS currency_id, currencies.name AS" +
            " currency_name, currencies.coefficient AS currency_coefficient, currencies.symbol AS currency_symbol FROM users" +
            " LEFT JOIN roles ON users.role_id = roles.id LEFT JOIN cities ON users.city_id = cities.id LEFT JOIN countries ON" +
            " cities.country_id = countries.id LEFT JOIN baskets ON users.id = baskets.user_id LEFT JOIN goods ON baskets.good_id = goods.id LEFT JOIN currencies ON countries.currency_id = currencies.id WHERE users.id = ?;";
    private static final String FIND_USER_BY_EMAIL_SQL = "SELECT users.id AS user_id, users.name AS user_name, users.surname, users.login, users.password," +
            " users.email, users.birth_date, users.role_id AS primary_key_role_id, users.city_id AS primary_key_city_id," +
            " roles.id AS role_id, roles.name AS role_name, cities.id AS city_id, cities.name AS city_name, cities.country_id AS" +
            " primary_key_country_id, countries.id AS country_id, countries.name AS country_name, baskets.good_id AS primary_key_good_id," +
            " baskets.user_id, baskets.quantity, goods.id AS good_id, goods.name AS good_name, goods.price AS good_price, goods.summary AS good_summary, goods.description AS good_description, goods.img AS good_img, countries.currency_id AS currency_id, currencies.name AS" +
            " currency_name, currencies.coefficient AS currency_coefficient, currencies.symbol AS currency_symbol FROM users" +
            " LEFT JOIN roles ON users.role_id = roles.id LEFT JOIN cities ON users.city_id = cities.id LEFT JOIN countries ON" +
            " cities.country_id = countries.id LEFT JOIN baskets ON users.id = baskets.user_id LEFT JOIN goods ON baskets.good_id = goods.id LEFT JOIN currencies ON countries.currency_id = currencies.id WHERE users.email = ?;";
    private static final String DELETE_USER_BY_ID_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER_BY_ID_SQL = "UPDATE users SET name = ?, surname = ?, login = ?, password = ?," +
            " email = ?, birth_date = ?, role_id = ?, city_id = ? WHERE id = ?;";

    private PropertiesReader propertiesReader;

    public NNSUserDao() {
        propertiesReader = new NNSProdPropertiesReader();
    }

    @Override
    public void setPropertiesReader(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
    }


    @Override
    public boolean add(User user) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            return fillUser(preparedStatement, user);
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public List<User> findAll() throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_SQL);

            while (!resultSet.isAfterLast()) {
                users.add(NNSDaoMapper.mapUser(resultSet));
            }

            return users;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public Optional<User> findById(long id) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = NNSDaoMapper.mapUser(resultSet);

            Optional<User> userOptional = Optional.empty();
            if (user.getId() != 0) {
                userOptional = Optional.of(user);
            }

            return userOptional;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_SQL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = NNSDaoMapper.mapUser(resultSet);

            Optional<User> userOptional = Optional.empty();
            if (user.getId() != 0) {
                userOptional = Optional.of(user);
            }

            return userOptional;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public boolean update(User user) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setLong(9, user.getId());
            return fillUser(preparedStatement, user);
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public boolean remove(long id) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    private boolean fillUser(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getLogin());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setDate(6, NNSDateConverter.convertLocalDateToDate(user.getBirthDate()));
        preparedStatement.setLong(7, user.getRole().getId());
        preparedStatement.setLong(8, user.getCity().getId());
        return preparedStatement.executeUpdate() == 1;
    }
}
