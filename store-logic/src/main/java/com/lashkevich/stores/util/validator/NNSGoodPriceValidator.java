package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.GoodPrice;

import java.math.BigDecimal;

public final class NNSGoodPriceValidator {
    private static final String MIN_PRICE = "0.1";

    private NNSGoodPriceValidator() {
    }

    public static boolean validate(GoodPrice goodPrice) {
        return goodPrice != null && validatePrice(goodPrice.getPrice()) && NNSCountryValidator.validate(goodPrice.getCountry()) &&
                NNSGoodValidator.validate(goodPrice.getGood());
    }

    private static boolean validatePrice(BigDecimal price) {
        return price != null && price.compareTo(new BigDecimal(MIN_PRICE)) >= 0;
    }
}
