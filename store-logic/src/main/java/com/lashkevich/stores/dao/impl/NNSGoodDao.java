package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.mapper.NNSDaoMapper;
import com.lashkevich.stores.entity.Good;
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

public class NNSGoodDao implements GoodDao {
    private static final String ADD_GOOD_SQL = "INSERT INTO goods (id, name, price, summary, description, img) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String FIND_ALL_GOODS_SQL = "SELECT goods.id AS good_id, goods.name AS good_name, goods.price AS good_price, goods.price ,goods.summary AS" +
            " good_summary, goods.description AS good_description, goods.img AS good_img FROM goods;";
    private static final String FIND_GOOD_BY_ID_SQL = "SELECT goods.id AS good_id, goods.name AS good_name, goods.price AS good_price, goods.summary AS" +
            " good_summary, goods.description AS good_description, goods.img AS good_img FROM goods WHERE id = ?;";
    private static final String UPDATE_GOOD_SQL = "UPDATE goods SET name = ?, price = ?, summary = ?, description = ?, img = ? WHERE id = ?;";
    private static final String DELETE_GOOD_BY_ID_SQL = "DELETE FROM goods WHERE id = ?;";

    private PropertiesReader propertiesReader;

    public NNSGoodDao() {
        propertiesReader = new NNSProdPropertiesReader();
    }

    @Override
    public void setPropertiesReader(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
    }

    @Override
    public boolean add(Good good) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_GOOD_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setLong(1, good.getId());
            preparedStatement.setString(2, good.getName());
            preparedStatement.setBigDecimal(3, good.getPrice());
            preparedStatement.setString(4, good.getSummary());
            preparedStatement.setString(5, good.getDescription());
            preparedStatement.setString(6, good.getImgURL());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public List<Good> findAll() throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             Statement statement = connection.createStatement()) {
            List<Good> goods = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_GOODS_SQL);

            while (resultSet.next()) {
                goods.add(NNSDaoMapper.mapGood(resultSet));
            }

            return goods;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public Optional<Good> findById(long id) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_GOOD_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Good good = new Good();

            while (resultSet.next()) {
                good = NNSDaoMapper.mapGood(resultSet);
            }

            Optional<Good> goodOptional = Optional.empty();
            if (good.getId() != 0) {
                goodOptional = Optional.of(good);
            }

            return goodOptional;
        } catch (SQLException | NNSConnectionPoolException e) {
            throw new NSSDaoStoreException(e);
        }
    }

    @Override
    public boolean update(Good good) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GOOD_SQL)) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setString(1, good.getName());
            preparedStatement.setBigDecimal(2, good.getPrice());
            preparedStatement.setString(3, good.getSummary());
            preparedStatement.setString(4, good.getDescription());
            preparedStatement.setString(5, good.getImgURL());
            preparedStatement.setLong(6, good.getId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }

    }

    @Override
    public boolean remove(long id) throws NSSDaoStoreException {
        try (Connection connection = NNSConnectionPool.getInstance().acquireConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GOOD_BY_ID_SQL);) {
            connection.setAutoCommit(propertiesReader.readCommitStatus());
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() >= 1;
        } catch (SQLException | NNSConnectionPoolException | NNSUtilException e) {
            throw new NSSDaoStoreException(e);
        }
    }
}
