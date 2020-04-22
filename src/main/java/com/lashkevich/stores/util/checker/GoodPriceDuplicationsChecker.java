package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.GoodPrice;

import java.util.List;

public class GoodPriceDuplicationsChecker {
    public static boolean addCheck(GoodPrice goodPrice, List<GoodPrice> goodPriceList) {
        for (GoodPrice goodPrices : goodPriceList) {
            if (goodPrice.getCountry().equals(goodPrices.getCountry()) && goodPrice.getGood().equals(goodPrices.getGood())) {
                return false;
            }
        }

        return true;
    }

    public static boolean updateCheck(GoodPrice goodPrice, List<GoodPrice> goodPriceList, long goodId, long countryId) {
        if (goodPrice.getCountry().getId() == countryId && goodPrice.getGood().getId() == goodId) {
            return true;
        }

        for (GoodPrice goodPrices : goodPriceList) {
            if (goodPrice.getGood().equals(goodPrices.getGood()) && goodPrice.getCountry().equals(goodPrices.getCountry())) {
                return false;
            }
        }

        return true;
    }
}
