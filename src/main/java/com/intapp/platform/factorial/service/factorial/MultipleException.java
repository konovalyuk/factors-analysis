package com.intapp.service.factorial;

/**
 * Created by Maxim_Konovaliuk on 8/31/2017.
 */
public class MultipleException extends RuntimeException {

    public MultipleException() {
    }

    public MultipleException(String message) {
        super(message);
    }

    public MultipleException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleException(Throwable cause) {
        super(cause);
    }

    public MultipleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
