package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.util.reader.PropertiesReader;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    void setPropertiesReader(PropertiesReader propertiesReader);

    boolean add(Role role) throws NSSDaoStoreException;

    List<Role> findAll() throws NSSDaoStoreException;

    Optional<Role> findById(long id) throws NSSDaoStoreException;

    boolean update(Role role) throws NSSDaoStoreException;

    boolean remove(long id) throws NSSDaoStoreException;
}
