package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Basket;

import java.util.List;

public class BasketDuplicationsChecker {
    public static boolean check(Basket basket, List<Basket> basketList) {
        return basketList.stream().
                noneMatch((currentBasket) -> (currentBasket.getGoods().entrySet().stream().
                        anyMatch((good) -> basket.getGoods().containsKey(good.getKey()))));
    }
}


