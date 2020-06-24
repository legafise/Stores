package com.lashkevich.stores.service;

import com.lashkevich.stores.dao.GoodDao;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.exception.DaoStoreException;
import com.lashkevich.stores.exception.ServiceStoreException;
import com.lashkevich.stores.service.impl.GoodServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GoodServiceImplTest {
    private GoodService goodService;
    private GoodDao goodDao;
    private Good firstTestGood;

    @Before
    public void setUp() {
        goodService = new GoodServiceImpl();
        goodService.setGoodDao(goodDao = mock(GoodDao.class));
        firstTestGood = new Good(1, "Samsung", "Samsung", "Samsung");
    }

    @Test
    public void addGoodPositiveTest() throws DaoStoreException, ServiceStoreException {
        when(goodDao.add(firstTestGood)).thenReturn(true);
        Assert.assertTrue(goodService.addGood(firstTestGood));
    }

    @Test
    public void addGoodNegativeTest() throws DaoStoreException, ServiceStoreException {
        firstTestGood.setName("A");
        when(goodDao.add(firstTestGood)).thenReturn(true);
        Assert.assertFalse(goodService.addGood(firstTestGood));
    }

    @Test
    public void findAllGoodsTest() throws DaoStoreException, ServiceStoreException {
        List<Good> goods = new ArrayList<>();
        goods.add(firstTestGood);
        when(goodDao.findAll()).thenReturn(goods);
        Assert.assertEquals(goods, goodService.findAllGoods());
    }

    @Test
    public void findGoodByIdPositiveTest() throws DaoStoreException, ServiceStoreException {
        when(goodDao.findById(1)).thenReturn(firstTestGood);
        Assert.assertEquals(firstTestGood, goodService.findGoodById("1"));
    }

    @Test(expected = ServiceStoreException.class)
    public void findGoodByIdWithInvalidGoodIdTest() throws ServiceStoreException {
        goodService.findGoodById("Roma");
    }

    @Test
    public void updateGoodPositiveTest() throws DaoStoreException, ServiceStoreException {
        firstTestGood.setName("Poopsung");
        when(goodDao.update(firstTestGood)).thenReturn(true);
        Assert.assertTrue(goodService.updateGood(firstTestGood));
    }

    @Test
    public void updateGoodNegativeTest() throws DaoStoreException, ServiceStoreException {
        firstTestGood.setName("");
        when(goodDao.update(firstTestGood)).thenReturn(true);
        Assert.assertFalse(goodService.updateGood(firstTestGood));
    }

    @Test
    public void removeGoodPositiveTest() throws ServiceStoreException, DaoStoreException {
        when(goodDao.remove(1)).thenReturn(true);
        Assert.assertTrue(goodService.removeGood("1"));
    }

    @Test (expected = ServiceStoreException.class)
    public void removeGoodWithInvalidRemoveTest() throws ServiceStoreException {
        goodService.removeGood("dfw");
    }
}
