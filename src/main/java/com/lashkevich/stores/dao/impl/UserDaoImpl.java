package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.dao.mapper.DaoMapper;
import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.ConnectionStoreException;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.converter.DateConverter;
import com.lashkevich.stores.util.provider.ConnectionProvider;
import com.lashkevich.stores.util.provider.impl.ConnectionProviderImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String ADD_USER_SQL = "INSERT INTO users (id, name, surname, login, password, email, " +
            "birth_date, role_id, city_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String FIND_ALL_USERS_SQL = "SELECT users.id AS user_id, users.name AS user_name, users.surname, users.login, users.password," +
            " users.email, users.birth_date, users.role_id AS primary_key_role_id, users.city_id AS primary_key_city_id," +
            " roles.id AS role_id, roles.name AS role_name, cities.id AS city_id, cities.name AS city_name, cities.country_id AS" +
            " primary_key_country_id, countries.id AS country_id, countries.name AS country_name, baskets.good_id AS primary_key_good_id," +
            " baskets.user_id, baskets.quantity, goods.id AS good_id, goods.name AS good_name, goods.summary AS good_summary, goods.description AS good_description FROM users" +
            " LEFT JOIN roles ON users.role_id = roles.id LEFT JOIN cities ON users.city_id = cities.id LEFT JOIN countries ON" +
            " cities.country_id = countries.id LEFT JOIN baskets ON users.id = baskets.user_id LEFT JOIN goods ON baskets.good_id = goods.id ORDER BY users.id;";
    private static final String FIND_USER_BY_ID_SQL = "SELECT users.id AS user_id, users.name AS user_name, users.surname, users.login, users.password," +
            " users.email, users.birth_date, users.role_id AS primary_key_role_id, users.city_id AS primary_key_city_id," +
            " roles.id AS role_id, roles.name AS role_name, cities.id AS city_id, cities.name AS city_name, cities.country_id AS" +
            " primary_key_country_id, countries.id AS country_id, countries.name AS country_name, baskets.good_id AS primary_key_good_id," +
            " baskets.user_id, baskets.quantity, goods.id AS good_id, goods.name AS good_name, goods.summary AS good_summary, goods.description AS good_description FROM users" +
            " INNER JOIN roles ON users.role_id = roles.id INNER JOIN cities ON users.city_id = cities.id INNER JOIN countries ON" +
            " cities.country_id = countries.id INNER JOIN baskets ON users.id = baskets.user_id INNER JOIN goods ON baskets.good_id = goods.id WHERE users.id = ?;";
    private static final String DELETE_USER_BY_ID_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER_BY_ID_SQL = "UPDATE users SET name = ?, surname = ?, login = ?, password = ?," +
            " email = ?, birth_date = ?, role_id = ?, city_id = ? WHERE id = ?;";

    private ConnectionProvider connectionProvider;

    public UserDaoImpl() {
        connectionProvider = new ConnectionProviderImpl();
    }

    public void setConnectionProvider(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public boolean add(User user) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setDate(7, DateConverter.convertLocalDateToDate(user.getBirthDate()));
            preparedStatement.setLong(8, user.getRole().getId());
            preparedStatement.setLong(9, user.getCity().getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public List<User> findAll() throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement()) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_SQL);

            while (!resultSet.isAfterLast()) {
                users.add(DaoMapper.mapUser(resultSet));
            }

            return users;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public User findById(long id) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return DaoMapper.mapUser(resultSet);
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public boolean update(User user) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setDate(6, DateConverter.convertLocalDateToDate(user.getBirthDate()));
            preparedStatement.setLong(7, user.getRole().getId());
            preparedStatement.setLong(8, user.getCity().getId());
            preparedStatement.setLong(9, user.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public boolean remove(long id) throws DaoStoreException {
        try (Connection connection = connectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_SQL)) {
            connection.setAutoCommit(connectionProvider.getCommitStatus());
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }
}
