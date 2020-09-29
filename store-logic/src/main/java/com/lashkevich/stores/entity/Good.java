package com.lashkevich.stores.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Good {
    private long id;
    private String name;
    private BigDecimal price;
    private String summary;
    private String description;
    private String imgURL;

    public Good() {
    }

    public Good(long id, String name, BigDecimal price, String summary, String description, String imgURL) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.summary = summary;
        this.description = description;
        this.imgURL = imgURL;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return id == good.id &&
                Objects.equals(name, good.name) &&
                Objects.equals(price, good.price) &&
                Objects.equals(summary, good.summary) &&
                Objects.equals(description, good.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, summary, description);
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
