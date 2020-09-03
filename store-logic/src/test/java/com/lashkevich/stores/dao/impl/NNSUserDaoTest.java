package com.lashkevich.stores.dao.impl;

import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.entity.*;
import com.lashkevich.stores.exception.NNSConnectionPoolException;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.pool.NNSConnectionPool;
import com.lashkevich.stores.util.reader.PropertiesReader;
import com.lashkevich.stores.util.reader.impl.NNSTestPropertiesReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NNSUserDaoTest {
    private User firstExpectedUser;
    private User secondExpectedUser;
    private User thirdExpectedUser;
    private User fourthExpectedUser;
    private User firstChangeExpectedUser;
    private UserDao userDao;

    @Before
    public void setUp() throws NNSConnectionPoolException {
        PropertiesReader testPropertiesReader = new NNSTestPropertiesReader();

        NNSConnectionPool.getInstance().setPropertiesReader(testPropertiesReader);
        NNSConnectionPool.getInstance().initializeConnectionPool(1);

        Map<Good, Integer> firstGoods = new HashMap<>();
        firstGoods.put(new Good(22, "Apple", "Apple", "Apple"), 2);
        firstGoods.put(new Good(23, "Android", "Android", "Android"), 1);
        Basket firstBasket = new Basket(firstGoods);
        firstExpectedUser = new User(8, "A", "B", "C", "D",
                "E", LocalDate.of(2000,02,20), new Role(1, "Admin"),
                new City(1, "Minsk", new Country(1, "Belarus")), firstBasket);

        firstChangeExpectedUser = new User(8, "A", "B", "C", "DOM",
                "E", LocalDate.of(2000,02,20), new Role(1, "Admin"),
                new City(1, "Minsk", new Country(1, "Belarus")), firstBasket);

        Map<Good, Integer> secondGoods = new HashMap<>();
        secondGoods.put(new Good(22, "Apple", "Apple", "Apple"), 2);
        secondGoods.put(new Good(23, "Android", "Android", "Android"), 1);
        Basket secondBasket = new Basket(secondGoods);
        secondExpectedUser = new User(9, "F", "G", "H", "I",
                "J", LocalDate.of(1111,11,11), new Role(2, "User"),
                new City(2, "Moscow", new Country(2, "Russia")), secondBasket);

        thirdExpectedUser = new User(10, "K", "L", "M", "N",
                "O", LocalDate.of(1010,10,10), new Role(2, "User"),
                new City(2, "Moscow", new Country(2, "Russia")));

        fourthExpectedUser = new User(11, "P", "U", "H", "C",
                "V", LocalDate.of(2010,04,12), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus")));

        userDao = new NNSUserDao();
        userDao.setPropertiesReader(testPropertiesReader);
    }

    @Test
    public void findByIdTest() throws NSSDaoStoreException {
        Assert.assertEquals(firstExpectedUser, userDao.findById(8).get());
    }

    @Test
    public void findAllTest() throws NSSDaoStoreException {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(firstExpectedUser);
        expectedUsers.add(secondExpectedUser);
        expectedUsers.add(thirdExpectedUser);
        Assert.assertEquals(expectedUsers, userDao.findAll());
    }

    @Test
    public void addTest() throws NSSDaoStoreException {
        Assert.assertTrue(userDao.add(fourthExpectedUser));
    }

    @Test
    public void updateTest() throws NSSDaoStoreException {
        Assert.assertTrue(userDao.update(firstChangeExpectedUser));
    }

    @Test
    public void removeTest() throws NSSDaoStoreException {
        Assert.assertTrue(userDao.remove(10));
    }
}
