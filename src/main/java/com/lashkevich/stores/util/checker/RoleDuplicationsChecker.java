package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Role;

import java.util.List;

public class RoleDuplicationsChecker {
    public static boolean check(Role role, List<Role> roleList) {
        for (Role currentRole : roleList) {
            if (currentRole.getName().equals(role.getName())) {
                return false;
            }
        }

        return true;
    }
}
