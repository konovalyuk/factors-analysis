package com.intapp.tms.service.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * The type Product does exist exception.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Product does exist")
public class ProductDoesExistException extends RuntimeException {

    private String message;

    /**
     * Instantiates a new Product not found exception.
     */
    public ProductDoesExistException() {
        super("Product does exist");
    }

    /**
     * Instantiates a new Product not found exception.
     *
     * @param message the tenant id
     */
    public ProductDoesExistException(String message) {
        super("Product does exist");
        this.message = message;
    }

    /**
     * Instantiates a new Product not found exception.
     *
     * @param message   the tenant id
     * @param throwable the throwable
     */
    public ProductDoesExistException(String message, Throwable throwable) {
        super("Product does exist", throwable);
        this.message = message;
    }

    /**
     * Instantiates a new Product not found exception.
     *
     * @param throwable the throwable
     */
    public ProductDoesExistException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " " + message;
    }
}
