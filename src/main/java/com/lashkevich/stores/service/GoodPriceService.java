package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.GoodPriceDao;
import com.lashkevich.stores.dao.impl.CountryDaoImpl;
import com.lashkevich.stores.dao.impl.GoodDaoImpl;
import com.lashkevich.stores.dao.impl.GoodPriceDaoImpl;
import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.util.checker.GoodPriceDuplicationsChecker;
import com.lashkevich.stores.util.validator.GoodPriceValidator;

import java.util.List;

public class GoodPriceService {
    private GoodPriceDao goodPriceDao;
    private GoodDao goodDao;
    private CountryDao countryDao;


    public GoodPriceService() {
        goodPriceDao = new GoodPriceDaoImpl();
        goodDao = new GoodDaoImpl();
        countryDao = new CountryDaoImpl();
    }

    public GoodPriceDao getGoodPriceDao() {
        return goodPriceDao;
    }

    public void setGoodPriceDao(GoodPriceDao goodPriceDao) {
        this.goodPriceDao = goodPriceDao;
    }

    public GoodDao getGoodDao() {
        return goodDao;
    }

    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public boolean addGoodPrice(GoodPrice goodPrice) throws ServiceStoreException {
        try {
            if (GoodPriceValidator.validate(goodPrice, countryDao.findAll(), goodDao.findAll()) && GoodPriceDuplicationsChecker.addCheck(goodPrice, goodPriceDao.findAll())) {
                return goodPriceDao.add(goodPrice);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    public List<GoodPrice> findAllGoodPrices() throws ServiceStoreException {
        try {
            return goodPriceDao.findAll();
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    public GoodPrice findGoodPriceByCountryAndGoodId(String countryId, String goodId) throws ServiceStoreException {
        try {
            return goodPriceDao.findByCountryAndGood(Long.parseLong(countryId), Long.parseLong(goodId));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    public boolean removeGoodPriceByCountryAndGoodId(String countryId, String goodId) throws ServiceStoreException {
        try {
            return goodPriceDao.remove(Long.parseLong(countryId), Long.parseLong(goodId));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    public boolean updateGoodPriceByCountryAndGoodId(String countryId, String goodId, GoodPrice goodPrice) throws ServiceStoreException {
        try {
            if (GoodPriceValidator.validate(goodPrice, countryDao.findAll(), goodDao.findAll()) &&
                    GoodPriceDuplicationsChecker.updateCheck(goodPrice, goodPriceDao.findAll(), Long.parseLong(goodId), Long.parseLong(countryId))) {
                return goodPriceDao.update(Long.parseLong(countryId), Long.parseLong(goodId), goodPrice);
            }

            return false;
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }
}
