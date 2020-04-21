package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CityDao;
import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.dao.impl.CityDaoImpl;
import com.lashkevich.stores.dao.impl.UserDaoImpl;
import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.util.checker.UserDuplicationsChecker;
import com.lashkevich.stores.util.validator.UserValidator;

import java.util.List;

public class UserService {
    private UserDao userDao;
    private CityDao cityDao;

    public UserService() {
        userDao = new UserDaoImpl();
        cityDao = new CityDaoImpl();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public CityDao getCityDao() {
        return cityDao;
    }

    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public User findUserById(String id) throws ServiceStoreException {
        try {
            return userDao.findById(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    public List<User> findAllUsers() throws ServiceStoreException {
        try {
            return userDao.findAll();
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    public boolean addUser(User user) throws ServiceStoreException {
        try {
            if (UserValidator.validate(user, cityDao.findAll()) && UserDuplicationsChecker.addCheck(userDao.findAll(), user)) {
                userDao.add(user);
                return true;
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    public boolean updateUser(User user) throws ServiceStoreException {
        try {
            if (UserValidator.validate(user, cityDao.findAll()) && UserDuplicationsChecker.updateCheck(userDao.findAll(), user)) {
                return userDao.update(user);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    public boolean removeUser(String id) throws ServiceStoreException {
        try {
            return userDao.remove(Long.parseLong(id));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }
}
