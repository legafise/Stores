package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.util.reader.PropertiesReader;

import java.util.List;
import java.util.Optional;

public interface CurrencyDao {
    void setPropertiesReader(PropertiesReader propertiesReader);

    boolean add(Currency currency) throws NSSDaoStoreException;

    List<Currency> findAll() throws NSSDaoStoreException;

    Optional<Currency> findById(long id) throws NSSDaoStoreException;

    Optional<Currency> findByName(String symbol) throws NSSDaoStoreException;

    boolean update(Currency currency) throws NSSDaoStoreException;

    boolean remove(long id) throws NSSDaoStoreException;
}
