package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.provider.ConnectionProvider;

import java.util.List;

public interface GoodDao {
    void setConnectionProvider(ConnectionProvider connectionProvider);

    boolean add(Good good) throws DaoStoreException;

    List<Good> findAll() throws DaoStoreException;

    Good findById(long id) throws DaoStoreException;

    boolean update(Good good) throws DaoStoreException;

    boolean remove(long id) throws DaoStoreException;
}
