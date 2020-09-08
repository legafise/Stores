package com.lashkevich.stores.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class GoodPrice {
    private Country country;
    private Good good;
    private BigDecimal price;

    public GoodPrice() {
    }

    public GoodPrice(Country country, Good good, BigDecimal price) {
        this.country = country;
        this.good = good;
        this.price = price;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodPrice goodPrice = (GoodPrice) o;
        return Objects.equals(country, goodPrice.country) &&
                Objects.equals(good, goodPrice.good) &&
                Objects.equals(price, goodPrice.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, good, price);
    }

    @Override
    public String toString() {
        return "GoodPrice{" +
                "country=" + country +
                ", good=" + good +
                ", price=" + price +
                '}';
    }
}
