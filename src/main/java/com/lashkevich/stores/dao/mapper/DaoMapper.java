package com.lashkevich.stores.dao.mapper;

import com.lashkevich.stores.entity.*;
import com.lashkevich.stores.util.converter.DateConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoMapper {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String BIRTH_DATE = "birth_date";
    private static final String ROLE_ID = "role_id";
    private static final String CITY_ID = "city_id";
    private static final String COUNTRY_ID = "country_id";
    private static final String SUMMARY = "summary";
    private static final String DESCRIPTION = "description";
    private static final String GOOD_ID = "good_id";
    private static final String USER_ID = "user_id";
    private static final String PRICE = "price";

    public static Role mapRole(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong(ID));
        role.setName(resultSet.getString(NAME));
        return role;
    }

    public static Country mapCountry(ResultSet resultSet) throws SQLException {
        Country country = new Country();
        country.setId(resultSet.getLong(ID));
        country.setName(resultSet.getString(NAME));
        return country;
    }

    public static User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ID));
        user.setName(resultSet.getString(NAME));
        user.setSurname(resultSet.getString(SURNAME));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setEmail(resultSet.getString(EMAIL));
        user.setBirthDate(DateConverter.convertDateToLocalDate(resultSet.getDate(BIRTH_DATE)));
        user.setRole(resultSet.getLong(ROLE_ID));
        user.setCity(resultSet.getLong(CITY_ID));
        return user;
    }

    public static City mapCity(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setId(resultSet.getLong(ID));
        city.setName(resultSet.getString(NAME));
        city.setCountry(resultSet.getLong(COUNTRY_ID));
        return city;
    }

    public static Good mapGood(ResultSet resultSet) throws SQLException {
        Good good = new Good();
        good.setId(resultSet.getLong(ID));
        good.setName(resultSet.getString(NAME));
        good.setSummary(resultSet.getString(SUMMARY));
        good.setDescription(resultSet.getString(DESCRIPTION));
        return good;
    }

    public static Basket mapBasket(ResultSet resultSet) throws SQLException {
        Basket basket = new Basket();
        basket.setGood(resultSet.getLong(GOOD_ID));
        basket.setUser(resultSet.getLong(USER_ID));
        return basket;
    }

    public static GoodPrice mapGoodPrice(ResultSet resultSet) throws SQLException {
        GoodPrice goodPrice = new GoodPrice();
        goodPrice.setGood(resultSet.getLong(GOOD_ID));
        goodPrice.setCountry(resultSet.getLong(COUNTRY_ID));
        goodPrice.setPrice(resultSet.getDouble(PRICE));
        return goodPrice;
    }
}
