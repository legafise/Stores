package com.lashkevich.stores.exception;

public class NNSConnectionPoolException extends Exception {
    public NNSConnectionPoolException() {
    }

    public NNSConnectionPoolException(String message) {
        super(message);
    }

    public NNSConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public NNSConnectionPoolException(Throwable cause) {
        super(cause);
    }

    protected NNSConnectionPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
