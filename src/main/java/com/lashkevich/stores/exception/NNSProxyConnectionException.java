package com.lashkevich.stores.exception;

public class NNSProxyConnectionException extends RuntimeException {
    public NNSProxyConnectionException() {
        super();
    }

    public NNSProxyConnectionException(String message) {
        super(message);
    }

    public NNSProxyConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NNSProxyConnectionException(Throwable cause) {
        super(cause);
    }

    protected NNSProxyConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
