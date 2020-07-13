package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Basket;

public final class NNSBasketValidator {
    private NNSBasketValidator() {
    }

    public static boolean validate(Basket basket) {
        return basket != null && basket.getGoods() != null;
    }
}
