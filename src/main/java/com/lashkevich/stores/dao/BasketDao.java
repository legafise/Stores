package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.util.reader.PropertiesReader;

import java.util.List;
import java.util.Optional;

public interface BasketDao {
    void setPropertiesReader(PropertiesReader propertiesReader);

    boolean add(Basket basket, long userId) throws NSSDaoStoreException;

    List<Basket> findAll() throws NSSDaoStoreException;

    Optional<Basket> findByUser(long id) throws NSSDaoStoreException;

    boolean update(Basket basket, long userId) throws NSSDaoStoreException;

    boolean remove(long userId) throws NSSDaoStoreException;
}
