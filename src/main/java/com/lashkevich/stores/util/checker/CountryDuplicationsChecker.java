package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Country;

import java.util.List;

public class CountryDuplicationsChecker {
    public static boolean check(Country country, List<Country> countryList) {
        for (Country currentCounty : countryList) {
            if (country.getName().equals(currentCounty.getName())) {
                return false;
            }
        }

        return true;
    }
}
