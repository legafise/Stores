package com.lashkevich.stores.command;

import com.lashkevich.stores.reciever.SiteReceiver;

public enum CommandType {
    CATALOG(Command -> SiteReceiver.getInstance().catalogForward());

    private Command command;
    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
