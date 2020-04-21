package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.entity.Good;

import java.util.List;
import java.util.Map;

public class BasketDuplicationsChecker {
    public static boolean check(Basket basket, List<Basket> basketList) {
        for (Basket baskets : basketList) {
            for (Map.Entry<Good, Integer> firstGoods : basket.getGoods().entrySet()) {
                for (Map.Entry<Good, Integer> secondGoods : baskets.getGoods().entrySet()) {
                    if (firstGoods.getKey().getId() == secondGoods.getKey().getId() && firstGoods.getValue().equals(secondGoods.getValue())) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
