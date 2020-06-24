package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.GoodPrice;

import java.math.BigDecimal;

public class GoodPriceValidator {
    private static final String MIN_PRICE = "0.1";

    public static boolean validate(GoodPrice goodPrice) {
        return goodPrice != null && validatePrice(goodPrice.getPrice()) && CountryValidator.validate(goodPrice.getCountry()) &&
                GoodValidator.validate(goodPrice.getGood());
    }

    private static boolean validatePrice(BigDecimal price) {
        return price != null && price.compareTo(new BigDecimal(MIN_PRICE)) >= 0;
    }
}
