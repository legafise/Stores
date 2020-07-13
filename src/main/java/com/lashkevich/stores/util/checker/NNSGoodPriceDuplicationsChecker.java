package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.GoodPrice;

import java.util.List;

public final class NNSGoodPriceDuplicationsChecker {
    private NNSGoodPriceDuplicationsChecker() {
    }

    public static boolean checkGoodAdding(GoodPrice goodPrice, List<GoodPrice> goodPriceList) {
        return goodPriceList.stream()
                .noneMatch(currentGoodPrice -> goodPrice.getCountry().equals(currentGoodPrice.getCountry()) &&
                        goodPrice.getGood().equals(currentGoodPrice.getGood()));
    }

    public static boolean checkGoodUpdating(GoodPrice goodPrice, List<GoodPrice> goodPriceList, long goodId, long countryId) {
        if (goodPrice.getCountry().getId() == countryId && goodPrice.getGood().getId() == goodId) {
            return true;
        }

        return goodPriceList.stream()
                .noneMatch(currentGoodPrice -> goodPrice.getGood().equals(currentGoodPrice.getGood()) &&
                        goodPrice.getCountry().equals(currentGoodPrice.getCountry()));
    }
}
