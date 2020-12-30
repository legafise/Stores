package com.lashkevich.stores.exception;

public class NNSReceiverException extends Exception {
    public NNSReceiverException() {

    }

    public NNSReceiverException(String message) {
        super(message);
    }

    public NNSReceiverException(String message, Throwable cause) {
        super(message, cause);
    }

    public NNSReceiverException(Throwable cause) {
        super(cause);
    }

    protected NNSReceiverException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
