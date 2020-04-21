package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Role;

public class RoleValidator {
    public static boolean validate(Role role) {
        return validateName(role.getName());
    }

    private static boolean validateName(String name) {
        if (name == null) {
            return false;
        }

        return name.length() >= 1 && name.length() <= 45;
    }
}
