package com.lashkevich.stores.command;

import com.lashkevich.stores.exception.NNSReceiverException;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    CommandResult execute(HttpServletRequest request) throws NNSReceiverException;
}
