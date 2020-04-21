package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class UserValidator {
    public static boolean validate(User user, List<City> cityList) {
        return validateName(user.getName()) && validateSurname(user.getSurname()) && validateLogin(user.getLogin()) &&
                validatePassword(user.getPassword()) && validateBirthDate(user.getBirthDate()) &&
                validateEmail(user.getEmail()) && validateCity(user.getCity(), cityList);
    }

    private static boolean validateName(String name) {
        if (name == null) {
            return false;
        }
        return name.length() >= 2 && name.length() <= 45;
    }

    public static boolean validateEmail(String email) {
        if (email == null || email.length() > 45) {
            return false;
        } return Pattern.matches("^[a-zA-Z0-9А-Яа-я](([a-zA-Z0-9А-Яа-я])|([^a-zA-Z0-9А-Яа-я@][a-zA-Z0-9А-Яа-я]))+@[^@.](([a-zA-Z0-9А-Яа-я])|([^a-zA-Z0-9А-Яа-я@!#$%&'*+/=?^_`{|}~][a-zA-Z0-9А-Яа-я]))+[.][^@.!#$%&'*+-/=?^_`{|}~]+$", email);
    }

    private static boolean validateSurname(String surname) {
        if (surname == null) {
            return false;
        }
        return surname.length() >= 2 && surname.length() <= 45;
    }

    private static boolean validateLogin(String login) {
        if (login == null) {
            return false;
        }
        return login.length() >= 2 && login.length() <= 45;
    }

    private static boolean validatePassword(String password) {
        if (password == null) {
            return false;
        }
        return password.length() >= 4 && password.length() <= 45;
    }


    private static boolean validateBirthDate(LocalDate birthDate) {
        if (birthDate == null || birthDate.getYear() > 2004) {
            return false;
        }
        return birthDate.getYear() >= 1920;
    }

    private static boolean validateCity(City city, List<City> cityList) {
        if (city == null) {
            return false;
        }

        for (City cities : cityList) {
            if (city.equals(cities)) {
                return true;
            }
        }

        return false;
    }
}
