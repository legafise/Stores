package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.Currency;

import java.math.BigDecimal;

public final class NNSCurrencyValidator {
    private static final String MIN_COEFFICIENT = "0";

    private NNSCurrencyValidator() {
    }

    public static boolean validateCurrency(Currency currency) {
        return currency != null && validateName(currency.getName()) && validateCoefficient(currency.getCoefficient()) &&
                validateSymbol(currency.getSymbol());
    }

    private static boolean validateName(String name) {
        return name != null && name.length() >= 1;
    }

    private static boolean validateCoefficient(BigDecimal coefficient) {
        return coefficient != null && coefficient.compareTo(new BigDecimal(MIN_COEFFICIENT)) > 0;
    }

    private static boolean validateSymbol(String symbol) {
        return symbol != null && symbol.length() >= 1;
    }
}
