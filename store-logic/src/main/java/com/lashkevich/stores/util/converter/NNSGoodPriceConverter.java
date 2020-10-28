package com.lashkevich.stores.util.converter;

import com.lashkevich.stores.entity.Currency;
import com.lashkevich.stores.entity.Good;

public final class NNSGoodPriceConverter {
    private NNSGoodPriceConverter() {
    }

    public static Good convert(Good good, Currency currency) {
        good.setPrice(good.getPrice().multiply(currency.getCoefficient()));
        return good;
    }
}
