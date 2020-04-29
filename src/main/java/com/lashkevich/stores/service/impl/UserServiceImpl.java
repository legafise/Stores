package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.dao.impl.CityDaoImpl;
import com.lashkevich.stores.dao.impl.UserDaoImpl;
import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.service.UserService;
import com.lashkevich.stores.util.checker.UserDuplicationsChecker;
import com.lashkevich.stores.util.validator.UserValidator;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private CityDao cityDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
        cityDao = new CityDaoImpl();
    }

    @Override
    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public CityDao getCityDao() {
        return cityDao;
    }

    @Override
    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public User findUserById(String id) throws ServiceStoreException {
        try {
            return userDao.findById(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public List<User> findAllUsers() throws ServiceStoreException {
        try {
            return userDao.findAll();
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public boolean addUser(User user) throws ServiceStoreException {
        try {
            if (UserValidator.validate(user) && UserDuplicationsChecker.checkUserAdding(userDao.findAll(), user)
                    && cityDao.findAll().contains(user.getCity())) {
                userDao.add(user);
                return true;
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws ServiceStoreException {
        try {
            if (UserValidator.validate(user) && UserDuplicationsChecker.checkUserUpdating(userDao.findAll(), user) &&
                    cityDao.findAll().contains(user.getCity())) {
                return userDao.update(user);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public boolean removeUser(String id) throws ServiceStoreException {
        try {
            return userDao.remove(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }
}
