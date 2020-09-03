package com.lashkevich.stores.exception;

public class NNSServiceStoreException extends Exception {
    public NNSServiceStoreException() {
    }

    public NNSServiceStoreException(String message) {
        super(message);
    }

    public NNSServiceStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public NNSServiceStoreException(Throwable cause) {
        super(cause);
    }

    public NNSServiceStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
