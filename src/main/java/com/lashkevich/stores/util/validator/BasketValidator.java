package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.entity.Good;

import java.util.Map;

public class BasketValidator {
    public static boolean validate(Basket basket) {
        return basket != null && validateGoods(basket.getGoods());
    }

    private static boolean validateGoods(Map <Good, Integer> goods) {
        if (goods == null) {
            return false;
        }

        for (Map.Entry<Good, Integer> good : goods.entrySet()) {
            if (!GoodValidator.validate(good.getKey()) || good.getValue() == 0) {
                return false;
            }
        }

        return true;
    }
}
