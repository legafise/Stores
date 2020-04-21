package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Basket;
import com.lashkevich.stores.entity.Good;
import com.lashkevich.stores.entity.User;

import java.util.List;
import java.util.Map;

public class BasketValidator {
    public static boolean validate(Basket basket, List<Good> goodList, Integer userId, List<User> userList) {
        return validateUserId(userId, userList) && validateGoods(basket, goodList);
    }

    private static boolean validateUserId(Integer userId, List<User> userList) {
        if (userId == null) {
            return false;
        }

        for (User users : userList) {
            if (userId == users.getId()) {
                return true;
            }
        }

        return false;
    }

    private static boolean validateGoods(Basket basket, List<Good> goodList) {
        if (basket == null) {
            return false;
        }

        int validGoodsCounter = 0;
        for (Map.Entry<Good, Integer> goods : basket.getGoods().entrySet()) {
            for (Good validGoods : goodList) {
                if (goods.getKey().equals(validGoods)) {
                    validGoodsCounter++;
                }
            }
        }

        return basket.getGoods().size() == validGoodsCounter;
    }
}
