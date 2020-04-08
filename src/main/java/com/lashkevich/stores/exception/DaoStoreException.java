package com.lashkevich.stores.exception;

public class DaoStoreException extends Exception {

    public DaoStoreException() {
    }

    public DaoStoreException(String message) {
        super(message);
    }

    public DaoStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoStoreException(Throwable cause) {
        super(cause);
    }

    public DaoStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
