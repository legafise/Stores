package com.lashkevich.stores.command;

import com.lashkevich.stores.exception.NNSCommandFactoryException;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private static final String UNKNOWN_COMMAND_ERROR_MESSAGE = "Unknown command: %s";
    private static final CommandFactory INSTANCE = new CommandFactory();


    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return INSTANCE;
    }

    public Command initCommand(HttpServletRequest request) throws NNSCommandFactoryException {
        String command = request.getParameter("command");
        if (CommandType.isExistCommand(command)) {
            return CommandType.valueOf(command.toUpperCase()).getCommand();
        }

        throw new NNSCommandFactoryException(String.format(UNKNOWN_COMMAND_ERROR_MESSAGE, command));
    }
}
