package com.lashkevich.stores.exception;

public class NNSMapperException extends Exception {
    public NNSMapperException() {

    }

    public NNSMapperException(String message) {
        super(message);
    }

    public NNSMapperException(String message, Throwable cause) {
        super(message, cause);
    }

    public NNSMapperException(Throwable cause) {
        super(cause);
    }

    protected NNSMapperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
