package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.dao.impl.NNSUserDao;
import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.service.CityService;
import com.lashkevich.stores.service.UserService;
import com.lashkevich.stores.util.checker.NNSUserDuplicationsChecker;
import com.lashkevich.stores.util.validator.NNSUserValidator;

import java.util.List;
import java.util.Optional;

public class NNSUserService implements UserService {
    private UserDao userDao;
    private CityService cityService;

    public NNSUserService() {
        userDao = new NNSUserDao();
        cityService = new NNSCityService();
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
    public CityService getCityService() {
        return cityService;
    }

    @Override
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public User findUserById(String id) throws NNSServiceStoreException {
        try {
            Optional<User> userOptional = userDao.findById(Long.parseLong(id));
            if (!userOptional.isPresent()) {
                throw new NNSServiceStoreException();
            }

            return userOptional.get();
        } catch (NumberFormatException | NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public List<User> findAllUsers() throws NNSServiceStoreException {
        try {
            return userDao.findAll();
        } catch (NSSDaoStoreException | NumberFormatException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean addUser(User user) throws NNSServiceStoreException {
        try {
            if (NNSUserValidator.validate(user) && NNSUserDuplicationsChecker.checkUserAdding(userDao.findAll(), user)
                    && cityService.findAllCities().contains(user.getCity())) {
                return userDao.add(user);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws NNSServiceStoreException {
        try {
            if (NNSUserValidator.validate(user) && NNSUserDuplicationsChecker.checkUserUpdating(userDao.findAll(), user) &&
                    cityService.findAllCities().contains(user.getCity())) {
                return userDao.update(user);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean removeUser(String id) throws NNSServiceStoreException {
        try {
            return userDao.remove(Long.parseLong(id));
        } catch (NSSDaoStoreException | NumberFormatException e) {
            throw new NNSServiceStoreException(e);
        }
    }
}
