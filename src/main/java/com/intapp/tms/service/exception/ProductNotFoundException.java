package com.intapp.tms.service.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Product not found exception.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Product not found")
public class ProductNotFoundException extends RuntimeException {


    private String message;

    /**
     * Instantiates a new Product not found exception.
     */
    public ProductNotFoundException() {
        super("Product not found");
    }

    /**
     * Instantiates a new Product not found exception.
     *
     * @param message the tenant id
     */
    public ProductNotFoundException(String message) {
        super("Product not found");
        this.message = message;
    }

    /**
     * Instantiates a new Product not found exception.
     *
     * @param message   the tenant id
     * @param throwable the throwable
     */
    public ProductNotFoundException(String message, Throwable throwable) {
        super("Product not found", throwable);
        this.message = message;
    }

    /**
     * Instantiates a new Product not found exception.
     *
     * @param throwable the throwable
     */
    public ProductNotFoundException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " " + message;
    }

}
