package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.ServiceStoreException;

import java.util.List;

public interface UserService {

    UserDao getUserDao();

    void setUserDao(UserDao userDao);

    CityDao getCityDao();

    void setCityDao(CityDao cityDao);

    User findUserById(String id) throws ServiceStoreException;

    List<User> findAllUsers() throws ServiceStoreException;

    boolean addUser(User user) throws ServiceStoreException;

    boolean updateUser(User user) throws ServiceStoreException;

    boolean removeUser(String id) throws ServiceStoreException;

}
