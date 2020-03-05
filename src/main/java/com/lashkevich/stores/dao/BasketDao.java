package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface BasketDao {
    void add(Basket basket) throws DaoStoreException;

    List<Basket> getAll() throws DaoStoreException;

    Basket getByUser(long id) throws DaoStoreException;

    void update(long good, long user, Basket basket) throws DaoStoreException;

    void remove(long goodId, long userId) throws DaoStoreException;
}
