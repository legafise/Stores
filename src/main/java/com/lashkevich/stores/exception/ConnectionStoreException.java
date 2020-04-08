package com.lashkevich.stores.exception;

public class ConnectionStoreException extends Exception {
    public ConnectionStoreException() {
    }

    public ConnectionStoreException(String message) {
        super(message);
    }

    public ConnectionStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionStoreException(Throwable cause) {
        super(cause);
    }

    public ConnectionStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
