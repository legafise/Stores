package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NNSUserDuplicationsCheckerTest {
    private User firstTestUser;
    private User firstChangeTestUser;
    private User secondTestUser;
    private User secondChangeTestUser;
    private List<User> testUserList;

    @Before
    public void setUp() {
        firstTestUser = new User(1, "df2", "dU", "Qd", "Casas",
                "t.a_p-0oc04@yan.dx.ru", LocalDate.of(2000, 04, 12), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus", new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN"))));
        firstChangeTestUser = new User(1, "df2", "dU", "Qd", "Casas",
                "tap0oc04@yandex.ru", LocalDate.of(2000, 04, 12), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus", new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN"))));
        secondTestUser = new User(2, "wfw", "thth", "dfg", "dfgdfg",
                "lashkevich@yandex.ru", LocalDate.of(2005, 10, 4), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus", new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN"))));
        secondChangeTestUser = new User(2, "wfw", "thth", "dfg", "dfgdfg",
                "t.a_p-0oc04@yan.dx.ru", LocalDate.of(2005, 10, 4), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus", new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN"))));
        testUserList = new ArrayList<>();
        testUserList.add(secondTestUser);
    }

    @Test
    public void checkUserAddingPositiveTest() {
        Assert.assertTrue(NNSUserDuplicationsChecker.checkUserAdding(testUserList, firstTestUser));
    }

    @Test
    public void checkUserAddingNegativeTest() {
        testUserList.add(firstChangeTestUser);
        Assert.assertFalse(NNSUserDuplicationsChecker.checkUserAdding(testUserList, firstTestUser));
    }

    @Test
    public void checkUserUpdatingPositiveTest() {
        testUserList.add(firstTestUser);
        Assert.assertTrue(NNSUserDuplicationsChecker.checkUserUpdating(testUserList, firstChangeTestUser));
    }

    @Test
    public void checkUserUpdatingNegativeTest() {
        testUserList.add(firstTestUser);
        Assert.assertFalse(NNSUserDuplicationsChecker.checkUserUpdating(testUserList, secondChangeTestUser));
    }
}