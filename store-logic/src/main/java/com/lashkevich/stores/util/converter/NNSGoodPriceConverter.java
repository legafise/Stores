package com.lashkevich.stores.util.converter;

import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.entity.Good;

import java.math.RoundingMode;

public final class NNSGoodPriceConverter {
    private static final int ROUNDED_QUANTITY = 1;

    private NNSGoodPriceConverter() {
    }

    public static Good convert(Good good, Currency currency) {
        good.setPrice(good.getPrice().multiply(currency.getCoefficient()).setScale(ROUNDED_QUANTITY, RoundingMode.FLOOR));
        return good;
    }
}
