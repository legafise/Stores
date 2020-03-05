package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface GoodDao {
    void add(Good good) throws DaoStoreException;

    List<Good> getAll() throws DaoStoreException;

    Good getById(long id) throws DaoStoreException;

    void update(Good good) throws DaoStoreException;

    void remove(long id) throws DaoStoreException;
}
