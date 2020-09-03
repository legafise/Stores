package com.lashkevich.stores.exception;

public class NSSDaoStoreException extends Exception {
    public NSSDaoStoreException() {
    }

    public NSSDaoStoreException(String message) {
        super(message);
    }

    public NSSDaoStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public NSSDaoStoreException(Throwable cause) {
        super(cause);
    }

    public NSSDaoStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
