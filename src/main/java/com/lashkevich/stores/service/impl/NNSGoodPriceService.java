package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.GoodPriceDao;
import com.lashkevich.stores.dao.impl.NNSGoodPriceDao;
import com.lashkevich.stores.entity.GoodPrice;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.service.CountryService;
import com.lashkevich.stores.service.GoodPriceService;
import com.lashkevich.stores.service.GoodService;
import com.lashkevich.stores.util.checker.NNSGoodPriceDuplicationsChecker;
import com.lashkevich.stores.util.validator.NNSGoodPriceValidator;

import java.util.List;
import java.util.Optional;

public class NNSGoodPriceService implements GoodPriceService {
    private GoodPriceDao goodPriceDao;
    private GoodService goodService;
    private CountryService countryService;

    public NNSGoodPriceService() {
        goodPriceDao = new NNSGoodPriceDao();
        goodService = new NNSGoodService();
        countryService = new NNSCountryService();
    }

    @Override
    public GoodPriceDao getGoodPriceDao() {
        return goodPriceDao;
    }

    @Override
    public void setGoodPriceDao(GoodPriceDao goodPriceDao) {
        this.goodPriceDao = goodPriceDao;
    }

    public GoodService getGoodService() {
        return goodService;
    }

    public void setGoodService(GoodService goodService) {
        this.goodService = goodService;
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public boolean addGoodPrice(GoodPrice goodPrice) throws NNSServiceStoreException {
        try {
            boolean isValidGoodPrice = NNSGoodPriceValidator.validate(goodPrice);
            boolean isDuplicateGood = NNSGoodPriceDuplicationsChecker.checkGoodAdding(goodPrice, goodPriceDao.findAll());

            if (isValidGoodPrice && countryService.findAllCountries().contains(goodPrice.getCountry()) &&
                    goodService.findAllGoods().contains(goodPrice.getGood()) && isDuplicateGood) {
                return goodPriceDao.add(goodPrice);
            }

            return false;
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public List<GoodPrice> findAllGoodPrices() throws NNSServiceStoreException {
        try {
            return goodPriceDao.findAll();
        } catch (NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public GoodPrice findGoodPriceByCountryAndGoodId(String countryId, String goodId) throws NNSServiceStoreException {
        try {
            Optional<GoodPrice> goodPriceOptional = goodPriceDao.findByCountryAndGood(Long.parseLong(countryId), Long.parseLong(goodId));
            if (!goodPriceOptional.isPresent()) {
                throw new NNSServiceStoreException();
            }

            return goodPriceOptional.get();
        } catch (NumberFormatException | NSSDaoStoreException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean removeGoodPriceByCountryAndGoodId(String countryId, String goodId) throws NNSServiceStoreException {
        try {
            return goodPriceDao.remove(Long.parseLong(countryId), Long.parseLong(goodId));
        } catch (NSSDaoStoreException | NumberFormatException e) {
            throw new NNSServiceStoreException(e);
        }
    }

    @Override
    public boolean updateGoodPriceByCountryAndGoodId(String countryId, String goodId, GoodPrice goodPrice) throws NNSServiceStoreException {
        try {
            boolean isValidGoodPrice = NNSGoodPriceValidator.validate(goodPrice);
            boolean isDuplicateGoodPrice = NNSGoodPriceDuplicationsChecker.checkGoodUpdating(goodPrice, goodPriceDao.findAll(),
                    Long.parseLong(goodId), Long.parseLong(countryId));

            if (isValidGoodPrice && countryService.findAllCountries().contains(goodPrice.getCountry()) &&
                    goodService.findAllGoods().contains(goodPrice.getGood()) && isDuplicateGoodPrice) {
                return goodPriceDao.update(Long.parseLong(countryId), Long.parseLong(goodId), goodPrice);
            }

            return false;
        } catch (NSSDaoStoreException | NumberFormatException e) {
            throw new NNSServiceStoreException(e);
        }
    }
}
