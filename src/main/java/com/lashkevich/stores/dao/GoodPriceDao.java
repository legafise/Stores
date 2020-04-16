package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.util.provider.ConnectionProvider;

import java.util.List;

public interface GoodPriceDao {
    void setConnectionProvider(ConnectionProvider connectionProvider);

    boolean add(GoodPrice goodPrice) throws DaoStoreException;

    List<GoodPrice> findAll() throws DaoStoreException;

    GoodPrice findByCountryAndGood(long countryId, long goodId) throws DaoStoreException;

    boolean update(long countryId, long goodId, GoodPrice goodPrice) throws DaoStoreException;

    boolean remove(long countryId, long goodId) throws DaoStoreException;
}
