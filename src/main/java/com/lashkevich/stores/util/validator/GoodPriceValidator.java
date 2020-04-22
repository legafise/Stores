package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Country;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.entity.GoodPrice;

import java.math.BigDecimal;
import java.util.List;

public class GoodPriceValidator {
    private static final String minPrice = "0.1";

    public static boolean validate(GoodPrice goodPrice, List<Country> countryList, List<Good> goodList) {
        return validatePrice(goodPrice.getPrice()) && validateCountry(goodPrice.getCountry(), countryList) &&
                validateGood(goodPrice.getGood(), goodList);
    }

    private static boolean validatePrice(BigDecimal price) {
        if (price == null) {
            return false;
        }

        return price.compareTo(new BigDecimal(minPrice)) >= 0;
    }

    private static boolean validateCountry(Country country, List<Country> countryList) {
        if (country == null) {
            return false;
        }

        for (Country countries : countryList) {
            if (country.equals(countries)) {
                return true;
            }
        }

        return false;
    }

    private static boolean validateGood(Good good, List<Good> goodList) {
        if (good == null) {
            return false;
        }

        for (Good goods : goodList) {
            if (good.equals(goods)) {
                return true;
            }
        }

        return false;
    }
}
