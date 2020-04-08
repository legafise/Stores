package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface RoleDao {
    boolean add(Role role) throws DaoStoreException;

    List<Role> findAll() throws DaoStoreException;

    Role findById(long id) throws DaoStoreException;

    boolean update(Role role) throws DaoStoreException;

    boolean remove(long id) throws DaoStoreException;
}
