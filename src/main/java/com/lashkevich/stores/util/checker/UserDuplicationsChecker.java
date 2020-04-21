package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDuplicationsChecker {
    public static boolean addCheck(List<User> usersList, User user) {
        for (User users : usersList) {
            if (user.getId() == users.getId() || user.getLogin().equals(users.getLogin()) ||
                    user.getEmail().equals(users.getEmail())) {
                return false;
            }
        }

        return true;
    }

    public static boolean updateCheck(List<User> usersList, User user) {
        List<User> duplicateUsers = new ArrayList<>();

        for (User users : usersList) {
            if (user.getLogin().equals(users.getLogin()) || user.getEmail().equals(users.getEmail())) {
                duplicateUsers.add(users);
            }
        }

        return duplicateUsers.size() == 1 && duplicateUsers.get(0).getId() == user.getId();
    }
}