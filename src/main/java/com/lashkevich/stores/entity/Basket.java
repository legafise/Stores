package com.lashkevich.stores.entity;

import java.util.Objects;

public class Basket {
    private long good;
    private long user;

    public Basket() {
    }

    public Basket(long good, long user) {
        this.good = good;
        this.user = user;
    }

    public long getGood() {
        return good;
    }

    public void setGood(long good) {
        this.good = good;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return good == basket.good &&
                user == basket.user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(good, user);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "good=" + good +
                ", user=" + user +
                '}';
    }
}
