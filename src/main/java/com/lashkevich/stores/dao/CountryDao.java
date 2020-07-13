package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.util.reader.PropertiesReader;

import java.util.List;
import java.util.Optional;

public interface CountryDao {
    void setPropertiesReader(PropertiesReader propertiesReader);

    boolean add(Country country) throws NSSDaoStoreException;

    List<Country> findAll() throws NSSDaoStoreException;

    Optional<Country> findById(long id) throws NSSDaoStoreException;

    boolean update(Country country) throws NSSDaoStoreException;

    boolean remove(long id) throws NSSDaoStoreException;
}
