package com.lashkevich.stores.util.checker;

import com.lashkevich.stores.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public final class NNSUserDuplicationsChecker {
    private NNSUserDuplicationsChecker() {
    }

    public static boolean checkUserAdding(List<User> usersList, User user) {
        return usersList.stream()
                .noneMatch(currentUser -> user.getId() == currentUser.getId() || user.getLogin().equals(currentUser.getLogin()) ||
                        user.getEmail().equals(currentUser.getEmail()));

    }

    public static boolean checkUserUpdating(List<User> usersList, User user) {
        List<User> duplicateUsers = usersList.stream()
                .filter(currentUser -> user.getLogin().equals(currentUser.getLogin()) ||
                user.getEmail().equals(currentUser.getEmail())).collect(Collectors.toList());

        return duplicateUsers.size() == 1 && duplicateUsers.get(0).getId() == user.getId();
    }
}
