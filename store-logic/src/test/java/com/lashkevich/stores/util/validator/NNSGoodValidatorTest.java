package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Good;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class NNSGoodValidatorTest {
    private Good testGood;

    @Before
    public void setUp() {
        testGood = new Good(1, "Samsung", new BigDecimal("2.0"), "Samsung", "Samsung", "Samsung");
    }

    @Test
    public void validatePositiveTest() {
        Assert.assertTrue(NNSGoodValidator.validate(testGood));
    }

    @Test
    public void validateNegativeTest() {
        testGood.setName("s");
        Assert.assertFalse(NNSGoodValidator.validate(testGood));
    }
}