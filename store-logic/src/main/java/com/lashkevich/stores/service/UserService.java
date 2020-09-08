package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.NNSServiceStoreException;

import java.util.List;

public interface UserService {

    UserDao getUserDao();

    void setUserDao(UserDao userDao);

    CityService getCityService();

    void setCityService(CityService cityService);

    User findUserById(String id) throws NNSServiceStoreException;

    List<User> findAllUsers() throws NNSServiceStoreException;

    boolean addUser(User user) throws NNSServiceStoreException;

    boolean updateUser(User user) throws NNSServiceStoreException;

    boolean removeUser(String id) throws NNSServiceStoreException;

}
