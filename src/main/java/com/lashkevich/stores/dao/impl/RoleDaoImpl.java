package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.mapper.DaoMapper;
import com.lashkevich.stores.dao.RoleDao;
import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.exception.ConnectionStoreException;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.connection.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private static final String ADD_ROLE_SQL = "INSERT INTO roles (id, name) VALUES (?, ?);";
    private static final String FIND_ALL_ROLES_SQL = "SELECT id, name FROM roles;";
    private static final String FIND_ROLE_BY_ID_SQL = "SELECT id, name FROM roles WHERE id = ?;";
    private static final String DELETE_ROLE_BY_ID_SQL = "DELETE FROM roles WHERE id = ?;";
    private static final String UPDATE_ROLE_SQL = "UPDATE roles SET name = ? WHERE id = ?;";

    @Override
    public void add(Role role) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ROLE_SQL)) {
            preparedStatement.setLong(1, role.getId());
            preparedStatement.setString(2, role.getName());
            preparedStatement.executeUpdate();
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public List<Role> getAll() throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            List<Role> roles = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_ROLES_SQL);

            while (resultSet.next()) {
                roles.add(DaoMapper.mapRole(resultSet));
            }

            return roles;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public Role getById(long id) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ROLE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Role role = new Role();

            while (resultSet.next()) {
                role = DaoMapper.mapRole(resultSet);
            }

            return role;
        } catch (ConnectionStoreException | SQLException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public void update(Role role) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE_SQL)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.setLong(2, role.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }

    @Override
    public void remove(long id) throws DaoStoreException {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROLE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionStoreException e) {
            throw new DaoStoreException(e);
        }
    }
}
