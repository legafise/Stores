package com.lashkevich.stores.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommandResult execute(HttpServletRequest request);
}
