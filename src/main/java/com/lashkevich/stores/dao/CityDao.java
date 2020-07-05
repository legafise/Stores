package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.util.reader.PropertiesReader;

import java.util.List;
import java.util.Optional;

public interface CityDao {
    void setPropertiesReader(PropertiesReader propertiesReader);

    boolean add(City city) throws NSSDaoStoreException;

    List<City> findAll() throws NSSDaoStoreException;

    Optional<City> findById(long id) throws NSSDaoStoreException;

    boolean update(City city) throws NSSDaoStoreException;

    boolean remove(long id) throws NSSDaoStoreException;
}
