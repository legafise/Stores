package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Basket;

public final class NNSBasketDuplicationsChecker {
    private NNSBasketDuplicationsChecker() {
    }

    public static boolean check(Basket currentUserBasket, Basket addUserBasket) {
        return addUserBasket.getGoods().entrySet().stream()
                .noneMatch(good -> currentUserBasket.getGoods().containsKey(good.getKey()));
    }
}
