package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Country;

public final class NNSCountryValidator {
    private NNSCountryValidator() {
    }

    public static boolean validate(Country country) {
        return country != null && validateName(country.getName());
    }

    private static boolean validateName(String name) {
        return name != null && name.length() >= 3 && name.length() <= 45;
    }
}
