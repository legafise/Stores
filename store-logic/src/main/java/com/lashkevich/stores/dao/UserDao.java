package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.util.reader.PropertiesReader;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void setPropertiesReader(PropertiesReader propertiesReader);

    boolean add(User user) throws NSSDaoStoreException;

    List<User> findAll() throws NSSDaoStoreException;

    Optional<User> findById(long id) throws NSSDaoStoreException;

    Optional<User> findByEmail(String email) throws NSSDaoStoreException;

    boolean update(User user) throws NSSDaoStoreException;

    boolean remove(long id) throws NSSDaoStoreException;
}
