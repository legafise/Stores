package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDuplicationsChecker {
    public static boolean checkUserAdding(List<User> usersList, User user) {
        for (User currentUser : usersList) {
            if (user.getId() == currentUser.getId() || user.getLogin().equals(currentUser.getLogin()) ||
                    user.getEmail().equals(currentUser.getEmail())) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkUserUpdating(List<User> usersList, User user) {
        List<User> duplicateUsers = new ArrayList<>();

        for (User currentUser : usersList) {
            if (user.getLogin().equals(currentUser.getLogin()) || user.getEmail().equals(currentUser.getEmail())) {
                duplicateUsers.add(currentUser);
            }
        }

        return duplicateUsers.size() == 1 && duplicateUsers.get(0).getId() == user.getId();
    }
}