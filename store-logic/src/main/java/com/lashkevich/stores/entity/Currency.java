package com.lashkevich.stores.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Currency {
    private long id;
    private String name;
    private BigDecimal coefficient;
    private String symbol;

    public Currency(long id, String name, BigDecimal coefficient, String symbol) {
        this.id = id;
        this.name = name;
        this.coefficient = coefficient;
        this.symbol = symbol;
    }

    public Currency() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return id == currency.id &&
                Objects.equals(name, currency.name) &&
                Objects.equals(coefficient, currency.coefficient) &&
                Objects.equals(symbol, currency.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coefficient, symbol);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coefficient=" + coefficient +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
