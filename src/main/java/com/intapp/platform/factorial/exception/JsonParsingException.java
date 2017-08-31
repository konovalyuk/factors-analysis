package com.intapp.platform.factorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by maksymk on 4/18/2017.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Json parsing exception")
public class JsonParsingException extends RuntimeException{

    private String message;

    /**
     * Instantiates a new Json parsing exception.
     */
    public JsonParsingException() {
        super("Json parsing exception");
    }

    /**
     * Instantiates a new Json parsing exception.
     *
     * @param message the message
     */
    public JsonParsingException(String message) {
        super("Json parsing exception");
        this.message = message;
    }

    /**
     * Instantiates a new Json parsing exception.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public JsonParsingException(String message, Throwable throwable) {
        super("Json parsing exception", throwable);
        this.message = message;
    }

    /**
     * Instantiates a new Json parsing exception.
     *
     * @param throwable the throwable
     */
    public JsonParsingException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " " + message;
    }
}
