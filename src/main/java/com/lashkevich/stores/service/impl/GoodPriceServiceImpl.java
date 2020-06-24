package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.CountryDao;
import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.dao.GoodPriceDao;
import com.lashkevich.stores.dao.impl.CountryDaoImpl;
import com.lashkevich.stores.dao.impl.GoodDaoImpl;
import com.lashkevich.stores.dao.impl.GoodPriceDaoImpl;
import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.service.GoodPriceService;
import com.lashkevich.stores.util.checker.GoodPriceDuplicationsChecker;
import com.lashkevich.stores.util.validator.GoodPriceValidator;

import java.util.List;

public class GoodPriceServiceImpl implements GoodPriceService {
    private GoodPriceDao goodPriceDao;
    private GoodDao goodDao;
    private CountryDao countryDao;

    public GoodPriceServiceImpl() {
        goodPriceDao = new GoodPriceDaoImpl();
        goodDao = new GoodDaoImpl();
        countryDao = new CountryDaoImpl();
    }

    @Override
    public GoodPriceDao getGoodPriceDao() {
        return goodPriceDao;
    }

    @Override
    public void setGoodPriceDao(GoodPriceDao goodPriceDao) {
        this.goodPriceDao = goodPriceDao;
    }

    @Override
    public GoodDao getGoodDao() {
        return goodDao;
    }

    @Override
    public void setGoodDao(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    @Override
    public CountryDao getCountryDao() {
        return countryDao;
    }

    @Override
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public boolean addGoodPrice(GoodPrice goodPrice) throws ServiceStoreException {
        try {
            if (GoodPriceValidator.validate(goodPrice) && countryDao.findAll().contains(goodPrice.getCountry()) &&
                    goodDao.findAll().contains(goodPrice.getGood()) && GoodPriceDuplicationsChecker.checkGoodAdding(goodPrice, goodPriceDao.findAll())) {
                return goodPriceDao.add(goodPrice);
            }

            return false;
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public List<GoodPrice> findAllGoodPrices() throws ServiceStoreException {
        try {
            return goodPriceDao.findAll();
        } catch (DaoStoreException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public GoodPrice findGoodPriceByCountryAndGoodId(String countryId, String goodId) throws ServiceStoreException {
        try {
            return goodPriceDao.findByCountryAndGood(Long.parseLong(countryId), Long.parseLong(goodId));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public boolean removeGoodPriceByCountryAndGoodId(String countryId, String goodId) throws ServiceStoreException {
        try {
            return goodPriceDao.remove(Long.parseLong(countryId), Long.parseLong(goodId));
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }

    @Override
    public boolean updateGoodPriceByCountryAndGoodId(String countryId, String goodId, GoodPrice goodPrice) throws ServiceStoreException {
        try {
            if (GoodPriceValidator.validate(goodPrice) && countryDao.findAll().contains(goodPrice.getCountry()) &&
                    goodDao.findAll().contains(goodPrice.getGood()) && GoodPriceDuplicationsChecker.checkGoodUpdating(goodPrice, goodPriceDao.findAll(), Long.parseLong(goodId), Long.parseLong(countryId))) {
                return goodPriceDao.update(Long.parseLong(countryId), Long.parseLong(goodId), goodPrice);
            }

            return false;
        } catch (DaoStoreException | NumberFormatException e) {
            throw new ServiceStoreException(e);
        }
    }
}
