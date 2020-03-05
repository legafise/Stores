package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface UserDao {
    void add(User user) throws DaoStoreException;

    List<User> getAll() throws DaoStoreException;

    User getById(long id) throws DaoStoreException;

    void update(User user) throws DaoStoreException;

    void remove(long id) throws DaoStoreException;
}
