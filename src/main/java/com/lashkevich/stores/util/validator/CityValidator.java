package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.entity.Country;

import java.util.List;

public class CityValidator {
    public static boolean validate(City city, List<Country> countries) {
        return validateName(city.getName()) && validateCountry(city.getCountry(), countries);
    }

    private static boolean validateName(String name) {
        if (name == null) {
            return false;
        }

        return name.length() >= 1 && name.length() <= 45;
    }

    private static boolean validateCountry(Country country, List<Country> countryList) {
        if (country == null) {
            return false;
        }

        for (Country countries : countryList) {
            if (country.getId() == countries.getId() && country.getName().equals(countries.getName())) {
                return true;
            }
        }

        return false;
    }
}
