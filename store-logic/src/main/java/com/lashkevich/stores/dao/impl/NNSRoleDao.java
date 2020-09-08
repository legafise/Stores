package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.RoleDao;
import com.lashkevich.stores.dao.mapper.NNSDaoMapper;
import com.lashkevich.stores.entity.Role;
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

public class NNSRoleDao implements RoleDao {
    private static final String ADD_ROLE_SQL = "INSERT INTO roles (id, name) VALUES (?, ?);";
    private static final String FIND_ALL_ROLES_SQL = "SELECT id AS role_id, name AS role_name FROM roles;";
    private static final String FIND_ROLE_BY_ID_SQL = "SELECT id AS role_id, name AS role_name  FROM roles WHERE id = ?;";
    private static final String DELETE_ROLE_BY_ID_SQL = "DELETE FROM roles WHERE id = ?;";
    private static final String UPDATE_ROLE_SQL = "UPDATE roles SET name = ? WHERE id = ?;";

    private PropertiesReader propertiesReader;

    public NNSRoleDao() {
        propertiesReader = new NNSProdPropertiesReader();
    }

    @Override
    public void setPropertiesReader(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
    }

    @Override
    public boolean add(Role role) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_ROLE_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setLong(1, role.getId());
            preparedStatement.setString(2, role.getName());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public List<Role> findAll() throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            List<Role> roles = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_ROLES_SQL);

            while (resultSet.next()) {
                roles.add(NNSDaoMapper.mapRole(resultSet));
            }

            return roles;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public Optional<Role> findById(long id) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ROLE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Optional<Role> roleOptional = Optional.empty();
            Role role = new Role();

            while (resultSet.next()) {
                role = NNSDaoMapper.mapRole(resultSet);
            }

            if (role.getId() != 0) {
                roleOptional = Optional.of(role);
            }

            return roleOptional;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public boolean update(Role role) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROLE_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setString(1, role.getName());
            preparedStatement.setLong(2, role.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public boolean remove(long id) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROLE_BY_ID_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }
}
