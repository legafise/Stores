package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.ConnectionStoreException;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.dao.mapper.DaoMapper;
import com.lashkevich.stores.util.connection.ConnectionUtil;
import com.lashkevich.stores.util.converter.DateConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String ADD_USER_SQL = "INSERT INTO users (id, name, surname, login, password, email, " +
            "birth_date, role_id, city_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String FIND_ALL_USERS_SQL = "SELECT id, name, surname, login, password, email, birth_date," +
            " role_id, city_id FROM users";
    private static final String FIND_USER_BY_ID_SQL = "SELECT id, name, surname, login, password, email, birth_date," +
            " role_id, city_id FROM users WHERE id = ?";
    private static final String DELETE_USER_BY_ID_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER_BY_ID_SQL = "UPDATE users SET name = ?, surname = ?, login = ?, password = ?," +
            " email = ?, birth_date = ?, role_id = ?, city_id = ? WHERE id = ?;";


    @Override
    public void add(User user) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_SQL)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setDate(7, DateConverter.convertLocalDateToDate(user.getBirthDate()));
            preparedStatement.setLong(8, user.getRole());
            preparedStatement.setLong(9, user.getCity());
            preparedStatement.executeUpdate();
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public List<User> getAll() throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()){
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_SQL);

            while (resultSet.next()) {
                users.add(DaoMapper.mapUser(resultSet));
            }

            return users;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public User getById(long id) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_SQL)){
            User user = new User();
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = (DaoMapper.mapUser(resultSet));
            }

            return user;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public void update(User user) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setDate(6, DateConverter.convertLocalDateToDate(user.getBirthDate()));
            preparedStatement.setLong(7, user.getRole());
            preparedStatement.setLong(8, user.getCity());
            preparedStatement.setLong(9, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public void remove(long id) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }
}
