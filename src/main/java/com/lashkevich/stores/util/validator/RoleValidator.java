package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Role;

public class RoleValidator {
    public static boolean validate(Role role) {
        return role != null && validateName(role.getName());
    }

    private static boolean validateName(String name) {
        return name != null && (name.length() >= 1 && name.length() <= 45);
    }
}
