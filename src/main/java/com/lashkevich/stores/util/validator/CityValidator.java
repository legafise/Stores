package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.entity.Country;

public class CityValidator {
    public static boolean validate(City city) {
        return city != null && validateName(city.getName()) && validateCountry(city.getCountry());
    }

    private static boolean validateName(String name) {
        return name != null && name.length() >= 1 && name.length() <= 45;
    }

    private static boolean validateCountry(Country country) {
        return CountryValidator.validate(country);
    }
}
