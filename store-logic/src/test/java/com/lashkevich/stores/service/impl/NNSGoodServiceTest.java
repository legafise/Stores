package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.CurrencyDao;
import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.service.CurrencyService;
import com.lashkevich.stores.service.GoodService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NNSGoodServiceTest {
    private GoodService goodService;
    private CurrencyService currencyService;
    private GoodDao goodDao;
    private CurrencyDao currencyDao;
    private Good firstTestGood;
    private Good firstTestGoodAfterConversion;
    private Optional<Good> firstTestGoodOptional;
    private Currency firstTestCurrency;
    private Optional<Currency> firstTestCurrencyOptional;

    @Before
    public void setUp() {
        goodService = new NNSGoodService();
        goodService.setGoodDao(goodDao = mock(GoodDao.class));
        currencyService = goodService.getCurrencyService();
        currencyService.setCurrencyDao(currencyDao = mock(CurrencyDao.class));
        firstTestGood = new Good(1, "Samsung", new BigDecimal("2.0"), "Samsung", "Samsung", "Samsung");
        firstTestGoodOptional = Optional.of(firstTestGood);
        firstTestGoodAfterConversion = new Good(1, "Samsung", new BigDecimal("5.20"), "Samsung", "Samsung", "Samsung");
        firstTestCurrency = new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN");
        firstTestCurrencyOptional = Optional.of(firstTestCurrency);
    }

    @Test
    public void addGoodPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(goodDao.add(firstTestGood)).thenReturn(true);
        Assert.assertTrue(goodService.addGood(firstTestGood));
    }

    @Test
    public void addGoodNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        firstTestGood.setName("A");
        when(goodDao.add(firstTestGood)).thenReturn(true);
        Assert.assertFalse(goodService.addGood(firstTestGood));
    }

    @Test
    public void findAllGoodsTest() throws NSSDaoStoreException, NNSServiceStoreException {
        List<Good> goods = new ArrayList<>();
        goods.add(firstTestGood);
        List<Good> goodsAfterConversion = new ArrayList<>();
        goodsAfterConversion.add(firstTestGoodAfterConversion);
        when(goodDao.findAll()).thenReturn(goods);
        when(currencyDao.findById(2)).thenReturn(firstTestCurrencyOptional);
        Assert.assertEquals(goodsAfterConversion, goodService.findAllGoods("2"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void findAllGoodsTestWithInvalidCurrencyIdTest() throws NNSServiceStoreException {
        goodService.findAllGoods("Roma");
    }

    @Test
    public void findGoodByIdPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(goodDao.findById(1)).thenReturn(firstTestGoodOptional);
        when(currencyDao.findById(2)).thenReturn(firstTestCurrencyOptional);
        Assert.assertEquals(firstTestGoodAfterConversion, goodService.findGoodById("1", "2"));
    }

    @Test(expected = NNSServiceStoreException.class)
    public void findGoodByIdWithInvalidGoodIdTest() throws NNSServiceStoreException {
        goodService.findGoodById("Roma", "1");
    }

    @Test
    public void updateGoodPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        firstTestGood.setName("Poopsung");
        when(goodDao.update(firstTestGood)).thenReturn(true);
        Assert.assertTrue(goodService.updateGood(firstTestGood));
    }

    @Test
    public void updateGoodNegativeTest() throws NSSDaoStoreException, NNSServiceStoreException {
        firstTestGood.setName("");
        when(goodDao.update(firstTestGood)).thenReturn(true);
        Assert.assertFalse(goodService.updateGood(firstTestGood));
    }

    @Test
    public void removeGoodPositiveTest() throws NNSServiceStoreException, NSSDaoStoreException {
        when(goodDao.remove(1)).thenReturn(true);
        Assert.assertTrue(goodService.removeGood("1"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void removeGoodWithInvalidRemoveTest() throws NNSServiceStoreException {
        goodService.removeGood("dfw");
    }
}
