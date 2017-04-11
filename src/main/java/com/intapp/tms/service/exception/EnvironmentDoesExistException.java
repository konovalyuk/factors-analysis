package com.intapp.tms.service.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Environment does exist exception.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Environment does exist")
public class EnvironmentDoesExistException extends RuntimeException {

    private String message;

    /**
     * Instantiates a new Environment not found exception.
     */
    public EnvironmentDoesExistException() {
        super("Environment does exist");
    }

    /**
     * Instantiates a new Environment not found exception.
     *
     * @param message the tenant id
     */
    public EnvironmentDoesExistException(String message) {
        super("Environment does exist");
        this.message = message;
    }

    /**
     * Instantiates a new Environment not found exception.
     *
     * @param message   the tenant id
     * @param throwable the throwable
     */
    public EnvironmentDoesExistException(String message, Throwable throwable) {
        super("Environment does exist", throwable);
        this.message = message;
    }

    /**
     * Instantiates a new Environment not found exception.
     *
     * @param throwable the throwable
     */
    public EnvironmentDoesExistException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " " + message;
    }

}
