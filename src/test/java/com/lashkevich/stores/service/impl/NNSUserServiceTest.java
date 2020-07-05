package com.lashkevich.stores.service.impl;

import com.lashkevich.stores.dao.UserDao;
import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.entity.User;
import com.lashkevich.stores.exception.NSSDaoStoreException;
import com.lashkevich.stores.exception.NNSServiceStoreException;
import com.lashkevich.stores.service.CityService;
import com.lashkevich.stores.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NNSUserServiceTest {
    private UserService userService;
    private UserDao userDao;
    private User firstTestUser;
    private Optional<User> firstTestUserOptional;
    private User firstChangeTestUser;
    private User secondTestUser;
    private CityService cityService;
    private City testCity;
    private List<City> cities;

    @Before
    public void setUp() {
        userService = new NNSUserService();
        userDao = mock(UserDao.class);
        cityService = mock(CityService.class);
        userService.setUserDao(userDao);
        userService.setCityService(cityService);
        firstTestUser = new User(1, "df2", "dU", "Qd", "Casas",
                "t.a_p-0oc04@yan.dx.ru", LocalDate.of(2000, 04, 12), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus")));
        firstTestUserOptional = Optional.of(firstTestUser);
        firstChangeTestUser = new User(1, "df2", "dU", "Qd", "Casas",
                "tap0oc04@yandex.ru", LocalDate.of(2000, 04, 12), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus")));
        secondTestUser = new User(2, "wfw", "thth", "dfg", "dfgdfg",
                "lashkevich@yandex.ru", LocalDate.of(2005, 10, 4), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus")));
        testCity = new City(1, "Minsk", new Country(1, "Belarus"));
        cities = new ArrayList<>();
        cities.add(testCity);
    }

    @Test
    public void findByIdTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(userDao.findById(1)).thenReturn(firstTestUserOptional);
        Assert.assertEquals(userService.findUserById("1"), firstTestUser);
    }

    @Test
    public void findAllTest() throws NSSDaoStoreException, NNSServiceStoreException {
        List<User> users = new ArrayList<>();
        users.add(firstTestUser);
        when(userDao.findAll()).thenReturn(users);
        Assert.assertEquals(userService.findAllUsers(), users);
    }

    @Test(expected = NNSServiceStoreException.class)
    public void findByIdWithInvalidUserIdTest() throws NNSServiceStoreException {
        userService.findUserById("s");
    }

    @Test
    public void addUserPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(userDao.add(firstTestUser)).thenReturn(true);
        when(cityService.findAllCities()).thenReturn(cities);
        Assert.assertTrue(userService.addUser(firstTestUser));
    }

    @Test
    public void addUserNegativeTest() throws NNSServiceStoreException, NSSDaoStoreException {
        firstTestUser.setEmail("tapoc04@@yandex.ru");
        when(cityService.findAllCities()).thenReturn(cities);
        Assert.assertFalse(userService.addUser(firstTestUser));
    }

    @Test
    public void updateUserPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        List<User> users = new ArrayList<>();
        users.add(firstTestUser);
        users.add(secondTestUser);
        when(userDao.findAll()).thenReturn(users);
        when(cityService.findAllCities()).thenReturn(cities);
        when(userDao.update(firstChangeTestUser)).thenReturn(true);
        Assert.assertTrue(userService.updateUser(firstChangeTestUser));
    }

    @Test
    public void updateUserNegativeTest() throws NNSServiceStoreException, NSSDaoStoreException {
        List<User> users = new ArrayList<>();
        users.add(firstTestUser);
        users.add(secondTestUser);
        when(userDao.findAll()).thenReturn(users);
        when(cityService.findAllCities()).thenReturn(cities);
        firstChangeTestUser.setEmail(null);
        when(userDao.update(firstChangeTestUser)).thenReturn(true);
        Assert.assertFalse(userService.updateUser(firstChangeTestUser));
    }

    @Test
    public void removeUserPositiveTest() throws NSSDaoStoreException, NNSServiceStoreException {
        when(userDao.remove(1)).thenReturn(true);
        Assert.assertTrue(userService.removeUser("1"));
    }

    @Test (expected = NNSServiceStoreException.class)
    public void removeUserWithInvalidRemoveTest() throws NNSServiceStoreException {
        Assert.assertFalse(userService.removeUser("ehgeh"));
    }
}
