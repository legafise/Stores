package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface CountryDao {
    boolean add(Country country) throws DaoStoreException;

    List<Country> findAll() throws DaoStoreException;

    Country findById(long id) throws DaoStoreException;

    boolean update(Country country) throws DaoStoreException;

    boolean remove(long id) throws DaoStoreException;
}
