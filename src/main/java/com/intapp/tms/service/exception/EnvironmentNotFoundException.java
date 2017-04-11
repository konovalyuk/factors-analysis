package com.intapp.tms.service.exception;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Environment not found exception.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Environment not found")
public class EnvironmentNotFoundException extends RuntimeException {

    private String message;

    /**
     * Instantiates a new Environment not found exception.
     */
    public EnvironmentNotFoundException() {
        super("Environment does exist");
    }

    /**
     * Instantiates a new Environment not found exception.
     *
     * @param message the tenant id
     */
    public EnvironmentNotFoundException(String message) {
        super("Environment not found");
        this.message = message;
    }

    /**
     * Instantiates a new Environment not found exception.
     *
     * @param message   the tenant id
     * @param throwable the throwable
     */
    public EnvironmentNotFoundException(String message, Throwable throwable) {
        super("Environment not found", throwable);
        this.message = message;
    }

    /**
     * Instantiates a new Environment not found exception.
     *
     * @param throwable the throwable
     */
    public EnvironmentNotFoundException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " " + message;
    }

}

