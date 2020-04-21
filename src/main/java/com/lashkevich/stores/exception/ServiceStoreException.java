package com.lashkevich.stores.exception;

public class ServiceStoreException extends Exception {
    public ServiceStoreException() {
    }

    public ServiceStoreException(String message) {
        super(message);
    }

    public ServiceStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceStoreException(Throwable cause) {
        super(cause);
    }

    public ServiceStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
