package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Good;

import java.math.BigDecimal;

public final class NNSGoodValidator {
    private static final String MIN_PRICE = "0";

    private NNSGoodValidator() {
    }

    public static boolean validate(Good good) {
        return good != null && validateDescription(good.getDescription()) && validateName(good.getName()) && validateSummary(good.getSummary()) &&
                validatePrice(good.getPrice()) && validateImg(good.getImgURL());
    }

    private static boolean validateName(String name) {
        return name != null && (name.length() >= 2 && name.length() <= 45);
    }

    private static boolean validateSummary(String summary) {
        return summary != null && (summary.length() >= 2 && summary.length() <= 45);
    }

    private static boolean validateDescription(String description) {
        return description != null && (description.length() >= 2 && description.length() <= 45);
    }

    private static boolean validatePrice(BigDecimal price) {
        return price != null && price.compareTo(new BigDecimal(MIN_PRICE)) > 0;
    }

    private static boolean validateImg(String imgUrl) {
        return imgUrl != null;
    }
}
