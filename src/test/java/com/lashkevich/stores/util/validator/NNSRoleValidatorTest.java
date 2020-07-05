package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NNSRoleValidatorTest {
    private Role testRole;

    @Before
    public void setUp() {
        testRole = new Role(1, "Admin");
    }

    @Test
    public void validatePositiveTest() {
        Assert.assertTrue(NNSRoleValidator.validate(testRole));
    }

    @Test
    public void validateNegativeTest() {
        testRole.setName("");
        Assert.assertFalse(NNSRoleValidator.validate(testRole));
    }
}