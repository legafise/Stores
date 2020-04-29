package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Good;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GoodValidatorTest {
    private Good testGood;

    @Before
    public void setUp() {
        testGood = new Good(1, "Samsung", "Samsung", "Samsung");
    }

    @Test
    public void validatePositiveTest() {
        Assert.assertTrue(GoodValidator.validate(testGood));
    }

    @Test
    public void validateNegativeTest() {
        testGood.setName("s");
        Assert.assertFalse(GoodValidator.validate(testGood));
    }
}