package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.provider.ConnectionProvider;

import java.util.List;

public interface CityDao {
    void setConnectionProvider(ConnectionProvider connectionProvider);

    boolean add(City city) throws DaoStoreException;

    List<City> findAll() throws DaoStoreException;

    City findById(long id) throws DaoStoreException;

    boolean update(City city) throws DaoStoreException;

    boolean remove(long id) throws DaoStoreException;
}
