package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RoleDuplicationsCheckerTest {
    private Role firstTestRole;
    private Role secondTestRole;
    private List<Role> testRoleList;

    @Before
    public void setUp() {
        firstTestRole = new Role(1, "Admin");
        secondTestRole = new Role(2, "User");
        testRoleList = new ArrayList<>();
        testRoleList.add(secondTestRole);
    }

    @Test
    public void checkPositiveTest() {
        Assert.assertTrue(RoleDuplicationsChecker.check(firstTestRole, testRoleList));
    }

    @Test
    public void checkNegativeTest() {
        testRoleList.add(firstTestRole);
        Assert.assertFalse(RoleDuplicationsChecker.check(firstTestRole, testRoleList));
    }
}