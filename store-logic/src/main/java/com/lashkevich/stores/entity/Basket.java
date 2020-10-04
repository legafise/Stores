package com.lashkevich.stores.entity;

import com.lashkevich.stores.util.converter.NNSGoodPriceConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Basket {
    private Map<Good, Integer> goods;

    public Basket() {
    }

    public Basket(Map<Good, Integer> goods) {
        this.goods = goods;
    }

    public Map<Good, Integer> getGoods() {
        return goods;
    }

    public void setGoods(Map<Good, Integer> goods) {
        this.goods = goods;
    }

    public Basket convertPrices(Currency currency) {
        goods.forEach((key, value) -> NNSGoodPriceConverter.convert(key, currency));
        this.goods = new HashMap<>(goods);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return Objects.equals(goods, basket.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goods);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "goods=" + goods +
                '}';
    }
}
