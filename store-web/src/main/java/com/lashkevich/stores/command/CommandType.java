package com.lashkevich.stores.command;

import com.lashkevich.stores.reciever.SiteReceiver;

import java.util.Arrays;

public enum CommandType {
    CATALOG(SiteReceiver.getInstance()::catalogForward, "catalog"),
    GOOD(SiteReceiver.getInstance()::goodForward, "good"),
    REGISTRATION_PAGE(SiteReceiver.getInstance()::registrationForward, "registration_page"),
    REGISTRATION(SiteReceiver.getInstance()::registration, "registration"),
    AUTHORIZATION_PAGE(SiteReceiver.getInstance()::authorizationForward, "authorization_page"),
    AUTHORIZATION(SiteReceiver.getInstance()::authorization, "authorization"),
    LOG_OUT(SiteReceiver.getInstance()::logOut, "log_out"),
    PROFILE(SiteReceiver.getInstance()::profileForward, "profile"),
    BASKET_PAGE(SiteReceiver.getInstance()::basketForward, "basket_page"),
    ADD_GOOD(SiteReceiver.getInstance()::addGoodInBasket, "add_good"),
    REMOVE_GOOD(SiteReceiver.getInstance()::removeGoodFromBasket, "remove_good"),
    CLEAR_BASKET(SiteReceiver.getInstance()::clearBasket, "clear_basket"),
    GUEST_INFO(SiteReceiver.getInstance()::guestInfoForward, "guest_info");


    private Command command;
    private String commandName;

    CommandType(Command command, String commandName) {
        this.command = command;
        this.commandName = commandName;
    }

    public Command getCommand() {
        return command;
    }

    public String getCommandName() {
        return commandName;
    }

    public static boolean isExistCommand(String commandName) {
        return commandName != null && Arrays.stream(CommandType.values())
                .anyMatch(currentCommandType -> currentCommandType.commandName.toUpperCase().equals(commandName.toUpperCase()));
    }
}
