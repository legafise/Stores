package com.lashkevich.stores.reciever;

import com.lashkevich.stores.command.CommandResult;

public class SiteReceiver {
    private static final SiteReceiver instance = new SiteReceiver();

    private SiteReceiver() {
    }

    public static SiteReceiver getInstance() {
        return instance;
    }

    public CommandResult catalogForward() {
        return new CommandResult(CommandResult.ResponseType.FORWARD, "/jsp/catalog.jsp");
    }

}
