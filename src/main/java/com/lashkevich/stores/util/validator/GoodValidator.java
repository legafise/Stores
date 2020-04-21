package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Good;

public class GoodValidator {
    public static boolean validateGood(Good good) {
        return validateDescription(good.getDescription()) && validateName(good.getName()) && validateSummary(good.getSummary());
    }

    private static boolean validateName(String name) {
        if (name == null) {
            return false;
        }
        return name.length() >= 2 && name.length() <= 45;
    }

    private static boolean validateSummary(String summary) {
        if (summary == null) {
            return false;
        }
        return summary.length() >= 2 && summary.length() <= 45;
    }

    private static boolean validateDescription(String description) {
        if (description == null) {
            return false;
        }
        return description.length() >= 2 && description.length() <= 45;
    }
}
