package com.lashkevich.stores.dao.mapper;

import com.lashkevich.stores.entity.*;
import com.lashkevich.stores.util.converter.NNSDateConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class NNSDaoMapper {
    private static final String SURNAME = "surname";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String BIRTH_DATE = "birth_date";
    private static final String ROLE_ID = "role_id";
    private static final String ROLE_NAME = "role_name";
    private static final String CITY_ID = "city_id";
    private static final String CITY_NAME = "city_name";
    private static final String COUNTRY_ID = "country_id";
    private static final String COUNTRY_NAME = "country_name";
    private static final String GOOD_ID = "good_id";
    private static final String GOOD_NAME = "good_name";
    private static final String GOOD_SUMMARY = "good_summary";
    private static final String GOOD_DESCRIPTION = "good_description";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String PRICE = "price";
    private static final String QUANTITY = "quantity";

    public static Role mapRole(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong(ROLE_ID));
        role.setName(resultSet.getString(ROLE_NAME));
        return role;
    }

    public static Country mapCountry(ResultSet resultSet) throws SQLException {
        Country country = new Country();
        country.setId(resultSet.getLong(COUNTRY_ID));
        country.setName(resultSet.getString(COUNTRY_NAME));
        return country;
    }

    public static User mapUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        while (resultSet.next()) {
            if (user.getId() != resultSet.getLong(USER_ID) && user.getId() != 0) {
                resultSet.previous();
                return user;
            }
            if (user.getId() != resultSet.getLong(USER_ID)) {
                fillUserData(resultSet, user);
            }

            if (resultSet.getString(GOOD_NAME) != null) {
                user.getBasket().getGoods().put(mapGood(resultSet), resultSet.getInt(QUANTITY));
            }
        }

        return user;
    }

    public static City mapCity(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setId(resultSet.getLong(CITY_ID));
        city.setName(resultSet.getString(CITY_NAME));
        city.setCountry(mapCountry(resultSet));
        return city;
    }

    public static Good mapGood(ResultSet resultSet) throws SQLException {
        Good good = new Good();
        good.setId(resultSet.getLong(GOOD_ID));
        good.setName(resultSet.getString(GOOD_NAME));
        good.setSummary(resultSet.getString(GOOD_SUMMARY));
        good.setDescription(resultSet.getString(GOOD_DESCRIPTION));
        return good;
    }

    public static Basket mapUserBasket(ResultSet resultSet) throws SQLException {
        Basket basket = new Basket();
        Map<Good, Integer> goods = new HashMap<>();

        while (resultSet.next()) {
            goods.put(mapGood(resultSet), resultSet.getInt(QUANTITY));
        }

        basket.setGoods(goods);
        return basket;
    }

    public static Basket mapAllBaskets(ResultSet resultSet) throws SQLException {
        Basket basket = new Basket();
        Map<Good, Integer> goods = new HashMap<>();
        goods.put(mapGood(resultSet), resultSet.getInt(QUANTITY));
        basket.setGoods(goods);
        return basket;
    }

    public static GoodPrice mapGoodPrice(ResultSet resultSet) throws SQLException {
        GoodPrice goodPrice = new GoodPrice();
        goodPrice.setCountry(mapCountry(resultSet));
        goodPrice.setGood(mapGood(resultSet));
        goodPrice.setPrice(resultSet.getBigDecimal(PRICE));
        return goodPrice;
    }

    private static void fillUserData(ResultSet resultSet, User user) throws SQLException {
        user.setId(resultSet.getLong(USER_ID));
        user.setName(resultSet.getString(USER_NAME));
        user.setSurname(resultSet.getString(SURNAME));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setEmail(resultSet.getString(EMAIL));
        user.setBirthDate(NNSDateConverter.convertDateToLocalDate(resultSet.getDate(BIRTH_DATE)));
        user.setRole(mapRole(resultSet));
        user.setCity(mapCity(resultSet));
    }
}
