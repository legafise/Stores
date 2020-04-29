package com.lashkevich.stores.util.validator;

import com.lashkevich.stores.entity.City;
import com.lashkevich.stores.entity.User;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class UserValidator {
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9А-Яа-я](([a-zA-Z0-9А-Яа-я])|([^a-zA-Z0-9А-Яа-я@][a-zA-Z0-9А-Яа-я]))" +
            "+@[^@.](([a-zA-Z0-9А-Яа-я])|([^a-zA-Z0-9А-Яа-я@!#$%&'*+/=?^_`{|}~][a-zA-Z0-9А-Яа-я]))+[.][^@.!#$%&'*+-/=?^_`{|}~]+$";

    public static boolean validate(User user) {
        return user != null && validateName(user.getName()) && validateSurname(user.getSurname()) && validateLogin(user.getLogin()) &&
                validatePassword(user.getPassword()) && validateBirthDate(user.getBirthDate()) &&
                validateEmail(user.getEmail()) && validateCity(user.getCity()) && BasketValidator.validate(user.getBasket());
    }

    private static boolean validateName(String name) {
        return name != null && name.length() >= 2 && name.length() <= 45;
    }

    private static boolean validateEmail(String email) {
        return email != null && email.length() <= 45 && Pattern.matches(EMAIL_PATTERN, email);
    }

    private static boolean validateSurname(String surname) {
        return surname != null && surname.length() >= 2 && surname.length() <= 45;
    }

    private static boolean validateLogin(String login) {
        return login != null && login.length() >= 2 && login.length() <= 45;
    }

    private static boolean validatePassword(String password) {
        return password != null && password.length() >= 4 && password.length() <= 45;
    }


    private static boolean validateBirthDate(LocalDate birthDate) {
        return birthDate != null && birthDate.getYear() <= 2004 && birthDate.getYear() >= 1920;
    }

    private static boolean validateCity(City city) {
        return CityValidator.validate(city);
    }
}
