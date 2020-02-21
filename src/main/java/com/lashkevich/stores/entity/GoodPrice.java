package com.lashkevich.stores.entity;

import java.util.Objects;

public class GoodPrice {
    private long country;
    private long good;
    private double price;

    public GoodPrice() {
    }

    public GoodPrice(long country, long good, double price) {
        this.country = country;
        this.good = good;
        this.price = price;
    }

    public long getCountry() {
        return country;
    }

    public void setCountry(long country) {
        this.country = country;
    }

    public long getGood() {
        return good;
    }

    public void setGood(long good) {
        this.good = good;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodPrice goodPrice = (GoodPrice) o;
        return country == goodPrice.country &&
                good == goodPrice.good &&
                Double.compare(goodPrice.price, price) == 0;
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
