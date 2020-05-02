package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.GoodPrice;

import java.util.List;

public class GoodPriceDuplicationsChecker {
    public static boolean checkGoodAdding(GoodPrice goodPrice, List<GoodPrice> goodPriceList) {
        for (GoodPrice currentGoodPrice : goodPriceList) {
            if (goodPrice.getCountry().equals(currentGoodPrice.getCountry()) && goodPrice.getGood().equals(currentGoodPrice.getGood())) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkGoodUpdating(GoodPrice goodPrice, List<GoodPrice> goodPriceList, long goodId, long countryId) {
        if (goodPrice.getCountry().getId() == countryId && goodPrice.getGood().getId() == goodId) {
            return true;
        }

        for (GoodPrice currentGoodPrice : goodPriceList) {
            if (goodPrice.getGood().equals(currentGoodPrice.getGood()) && goodPrice.getCountry().equals(currentGoodPrice.getCountry())) {
                return false;
            }
        }

        return true;
    }
}
