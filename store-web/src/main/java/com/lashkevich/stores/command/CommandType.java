package com.lashkevich.stores.command;

import com.lashkevich.stores.reciever.SiteReceiver;

import java.util.Arrays;

public enum CommandType {
    CATALOG(SiteReceiver.getInstance()::catalogForward, "catalog"),
    GOOD(SiteReceiver.getInstance()::goodForward, "good"),
    REGISTRATION_PAGE(SiteReceiver.getInstance()::registrationForward, "registration_page"),
    REGISTRATION(SiteReceiver.getInstance()::registration, "registration"),
    AUTHORIZATION_PAGE(SiteReceiver.getInstance()::authorizationForward, "authorization_page");


    private Command command;
    private String commandName;

    CommandType(Command command, String commandName) {
        this.command = command;
        this.commandName = commandName;
    }

    public Command getCommand() {
        return command;
    }

    public static boolean isExistCommand(String commandName) {
        return commandName != null && Arrays.stream(CommandType.values())
                .anyMatch(currentCommandType -> currentCommandType.commandName.toUpperCase().equals(commandName.toUpperCase()));
    }
}
