package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface GoodPriceDao {
    boolean add(GoodPrice goodPrice) throws DaoStoreException;

    List<GoodPrice> findAll() throws DaoStoreException;

    GoodPrice findByCountryAndGood(long countryId, long goodId) throws DaoStoreException;

    boolean update(long countryId, long goodId, GoodPrice goodPrice) throws DaoStoreException;

    boolean remove(long countryId, long goodId) throws DaoStoreException;
}
