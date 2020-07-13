package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.util.reader.PropertiesReader;

import java.util.List;
import java.util.Optional;

public interface GoodPriceDao {
    void setPropertiesReader(PropertiesReader propertiesReader);

    boolean add(GoodPrice goodPrice) throws NSSDaoStoreException;

    List<GoodPrice> findAll() throws NSSDaoStoreException;

    Optional<GoodPrice> findByCountryAndGood(long countryId, long goodId) throws NSSDaoStoreException;

    boolean update(long countryId, long goodId, GoodPrice goodPrice) throws NSSDaoStoreException;

    boolean remove(long countryId, long goodId) throws NSSDaoStoreException;
}
