package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.util.reader.PropertiesReader;

import java.util.List;
import java.util.Optional;

public interface GoodDao {
    void setPropertiesReader(PropertiesReader propertiesReader);

    boolean add(Good good) throws NSSDaoStoreException;

    List<Good> findAll() throws NSSDaoStoreException;

    Optional<Good> findById(long id) throws NSSDaoStoreException;

    boolean update(Good good) throws NSSDaoStoreException;

    boolean remove(long id) throws NSSDaoStoreException;
}
