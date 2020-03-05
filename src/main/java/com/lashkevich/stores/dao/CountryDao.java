package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface CountryDao {
    void add(Country country) throws DaoStoreException;

    List<Country> getAll() throws DaoStoreException;

    Country getById(long id) throws DaoStoreException;

    void update(Country country) throws DaoStoreException;

    void remove(long id) throws DaoStoreException;
}
