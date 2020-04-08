package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface CityDao {
    boolean add(City city) throws DaoStoreException;

    List<City> findAll() throws DaoStoreException;

    City findById(long id) throws DaoStoreException;

    boolean update(City city) throws DaoStoreException;

    boolean remove(long id) throws DaoStoreException;
}
