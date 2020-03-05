package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface RoleDao {
    void add(Role role) throws DaoStoreException;

    List<Role> getAll() throws DaoStoreException;

    Role getById(long id) throws DaoStoreException;

    void update(Role role) throws DaoStoreException;

    void remove(long id) throws DaoStoreException;
}