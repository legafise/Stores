package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface BasketDao {
    boolean add(Basket basket, long userId) throws DaoStoreException;

    List<Basket> findAll() throws DaoStoreException;

    List<Basket> findByUser(long id) throws DaoStoreException;

    boolean update(Basket basket, long userId) throws DaoStoreException;

    boolean remove(long userId) throws DaoStoreException;
}
