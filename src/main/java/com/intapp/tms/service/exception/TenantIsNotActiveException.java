package com.intapp.tms.service.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Tenant is not active exception.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Tenant is not active")
public class TenantIsNotActiveException extends RuntimeException {


    private String message;

    /**
     * Instantiates a new TenantIsNotActive not found exception.
     */
    public TenantIsNotActiveException() {
        super("Tenant is not active");
    }

    /**
     * Instantiates a new TenantIsNotActive not found exception.
     *
     * @param message the tenant id
     */
    public TenantIsNotActiveException(String message) {
        super("Tenant is not active");
        this.message = message;
    }

    /**
     * Instantiates a new TenantIsNotActive not found exception.
     *
     * @param message   the tenant id
     * @param throwable the throwable
     */
    public TenantIsNotActiveException(String message, Throwable throwable) {
        super("Tenant is not active", throwable);
        this.message = message;
    }

    /**
     * Instantiates a new TenantIsNotActive not found exception.
     *
     * @param throwable the throwable
     */
    public TenantIsNotActiveException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " " + message;
    }

}
