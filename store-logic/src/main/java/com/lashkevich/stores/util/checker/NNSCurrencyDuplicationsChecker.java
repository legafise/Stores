package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.Currency;

import java.util.List;
import java.util.stream.Collectors;

public final class NNSCurrencyDuplicationsChecker {
    private NNSCurrencyDuplicationsChecker() {
    }

    public static boolean checkCurrencyAdding(Currency currency, List<Currency> currencyList) {
        return currencyList.stream()
                .noneMatch(currentCurrency -> currency.getId() == currentCurrency.getId() || currency.getName().toUpperCase().equals(currentCurrency.getName().toUpperCase()) ||
                        currency.getSymbol().equals(currentCurrency.getSymbol()));
    }

    public static boolean checkCurrencyUpdating(Currency currency, List<Currency> currencyList) {
        List<Currency> duplicateCurrencies = currencyList.stream()
                .filter(currentCurrency -> currency.getName().toUpperCase().equals(currentCurrency.getName().toUpperCase()) ||
                        currency.getSymbol().equals(currentCurrency.getSymbol())).collect(Collectors.toList());

        return duplicateCurrencies.size() == 1 && duplicateCurrencies.get(0).getId() == currency.getId();
    }
}
