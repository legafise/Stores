package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Role;
import com.lashkevich.stores.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class UserValidatorTest {
    private User testUser;

    @Before
    public void setUp() {
        testUser = new User(1, "df2", "dU", "Qd", "Casas",
                "t.a_p-0oc04@yan.dx.ru", LocalDate.of(2000, 04, 12), new Role(2, "User"),
                new City(1, "Minsk", new Country(1, "Belarus")));
    }

    @Test
    public void validatePositiveTest() {
        Assert.assertTrue(UserValidator.validate(testUser));
    }

    @Test
    public void validateNegativeTest() {
        testUser.setEmail("roma");
        Assert.assertFalse(UserValidator.validate(testUser));
    }
}