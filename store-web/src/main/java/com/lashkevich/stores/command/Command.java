package com.lashkevich.stores.command;

import com.lashkevich.stores.exception.NNSReceiverException;
import com.lashkevich.stores.exception.NNSServiceStoreException;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    CommandResult execute(HttpServletRequest request) throws NNSReceiverException, NNSServiceStoreException, InterruptedException;
}
