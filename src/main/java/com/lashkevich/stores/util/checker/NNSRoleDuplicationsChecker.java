package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Role;

import java.util.List;

public final class NNSRoleDuplicationsChecker {
    private NNSRoleDuplicationsChecker() {
    }

    public static boolean check(Role role, List<Role> roleList) {
        return roleList.stream()
                .noneMatch(currentRole -> currentRole.getName().equals(role.getName()));
    }
}
