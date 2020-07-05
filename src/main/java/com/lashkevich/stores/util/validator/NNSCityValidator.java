package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.City;

public final class NNSCityValidator {
    private NNSCityValidator() {
    }

    public static boolean validate(City city) {
        return city != null && validateName(city.getName()) && NNSCountryValidator.validate(city.getCountry());
    }

    private static boolean validateName(String name) {
        return name != null && name.length() >= 1 && name.length() <= 45;
    }
}
