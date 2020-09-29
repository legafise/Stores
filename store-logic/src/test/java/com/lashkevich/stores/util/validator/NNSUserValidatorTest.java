package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NNSUserValidatorTest {
    private User testUser;

    @Before
    public void setUp() {
        testUser = new User(1, "df2", "dU", "Qd", "Casas",
                "t.a_p-0oc04@yan.dx.ru", LocalDate.of(2000, 04, 12), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus", new Currency(2, "Belarusian ruble", new BigDecimal("2.6"), "BYN"))));
    }

    @Test
    public void validatePositiveTest() {
        Assert.assertTrue(NNSUserValidator.validate(testUser));
    }

    @Test
    public void validateNegativeTest() {
        testUser.setEmail("roma");
        Assert.assertFalse(NNSUserValidator.validate(testUser));
    }
}