package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Country;

public class CountryValidator {
    public static boolean validate(Country country) {
        return validateName(country.getName());
    }

    private static boolean validateName(String name) {
        if (name == null) {
            return false;
        }

        return name.length() >= 3 && name.length() <= 45;
    }
}
