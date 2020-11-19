package com.lashkevich.stores.exception;

public class NNSCommandFactoryException extends Exception {
    public NNSCommandFactoryException() {

    }

    public NNSCommandFactoryException(String message) {
        super(message);
    }

    public NNSCommandFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NNSCommandFactoryException(Throwable cause) {
        super(cause);
    }

    protected NNSCommandFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
