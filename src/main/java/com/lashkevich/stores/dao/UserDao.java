package com.lashkevich.stores.dao;

import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.DaoStoreException;

import java.util.List;

public interface UserDao {
    boolean add(User user) throws DaoStoreException;

    List<User> findAll() throws DaoStoreException;

    User findById(long id) throws DaoStoreException;

    boolean update(User user) throws DaoStoreException;

    boolean remove(long id) throws DaoStoreException;
}
