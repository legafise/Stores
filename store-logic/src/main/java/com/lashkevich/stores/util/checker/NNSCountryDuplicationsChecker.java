package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Country;

import java.util.List;

public final class NNSCountryDuplicationsChecker {
    private NNSCountryDuplicationsChecker() {
    }

    public static boolean check(Country country, List<Country> countryList) {
        return countryList.stream()
                .noneMatch(currentCountry -> country.getName().equals(currentCountry.getName()));
    }
}
