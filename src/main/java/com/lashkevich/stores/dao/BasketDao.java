package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface BasketDao {
    void add(Basket basket, long userId) throws DaoStoreException;

    List<Basket> findAll() throws DaoStoreException;

    Basket findByUser(long id) throws DaoStoreException;

    void update(Basket basket, long userId) throws DaoStoreException;

    void remove(long userId) throws DaoStoreException;
}
