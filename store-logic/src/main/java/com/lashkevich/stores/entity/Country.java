package com.lashkevich.stores.entity;

import java.util.Objects;

public class Country {
    private long id;
    private String name;
    private Currency currency;

    public Country(long id, String name, Currency currency) {
        this.id = id;
        this.name = name;
        this.currency = currency;
    }

    public Country() {
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id &&
                Objects.equals(name, country.name) &&
                Objects.equals(currency, country.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, currency);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currency=" + currency +
                '}';
    }
}
