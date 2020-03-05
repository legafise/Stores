package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface CityDao {
    void add(City city) throws DaoStoreException;

    List<City> getAll() throws DaoStoreException;

    City getById(long id) throws DaoStoreException;

    void update(City city) throws DaoStoreException;

    void remove(long id) throws DaoStoreException;
}
