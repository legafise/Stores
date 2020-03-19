package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface GoodPriceDao {
    void add(GoodPrice goodPrice) throws DaoStoreException;

    List<GoodPrice> findAll() throws DaoStoreException;

    GoodPrice findByCountryAndGood(long countryId, long goodId) throws DaoStoreException;

    void update(long countryId, long goodId, GoodPrice goodPrice) throws DaoStoreException;

    void remove(long countryId, long goodId) throws DaoStoreException;
}
