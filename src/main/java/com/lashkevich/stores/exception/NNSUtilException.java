package com.lashkevich.stores.exception;

public class NNSUtilException extends Exception {
    public NNSUtilException() {
        super();
    }

    public NNSUtilException(String message) {
        super(message);
    }

    public NNSUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public NNSUtilException(Throwable cause) {
        super(cause);
    }

    protected NNSUtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
