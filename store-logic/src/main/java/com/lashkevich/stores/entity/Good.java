package com.lashkevich.stores.entity;

import java.util.Objects;

public class Good {
    private long id;
    private String name;
    private String summary;
    private String description;

    public Good() {
    }

    public Good(long id, String name, String summary, String description) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.description = description;
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
                Objects.equals(summary, good.summary) &&
                Objects.equals(description, good.description);
    }

    @Override
    public int hashCode() {
        int code = 32;
        code += id;
        code += name.hashCode();
        code += summary.hashCode();
        code += description.hashCode();

        return code;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
