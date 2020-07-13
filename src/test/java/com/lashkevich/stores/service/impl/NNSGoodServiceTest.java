package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.service.GoodService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NNSGoodServiceTest {
    private GoodService goodService;
    private GoodDao goodDao;
    private Good firstTestGood;
    private Optional<Good> firstTestGoodOptional;

    @Before
    public void setUp() {
        goodService = new NNSGoodService();
        goodService.setGoodDao(goodDao = mock(GoodDao.class));
        firstTestGood = new Good(1, "Samsung", "Samsung", "Samsung");
        firstTestGoodOptional = Optional.of(firstTestGood);
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
        when(goodDao.findAll()).thenReturn(goods);
        Assert.assertEquals(goods, goodService.findAllGoods());
    }

    @Test
    public void findGoodByIdPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(goodDao.findById(1)).thenReturn(firstTestGoodOptional);
        Assert.assertEquals(firstTestGood, goodService.findGoodById("1"));
    }

    @Test(expected = NNSServiceStoreException.class)
    public void findGoodByIdWithInvalidGoodIdTest() throws NNSServiceStoreException {
        goodService.findGoodById("Roma");
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
