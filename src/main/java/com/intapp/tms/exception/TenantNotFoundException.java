package com.intapp.tms.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Tenant not found exception.
 */
@RequiredArgsConstructor
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="No such Tenant")
public class TenantNotFoundException extends RuntimeException {

    private String tenantId;

    /**
     * Instantiates a new Tenant not found exception.
     *
     * @param tenantId the tenant id
     */
    public TenantNotFoundException(String tenantId) {
        super("Could not find the tenant.");
        this.tenantId = tenantId;
    }

    /**
     * Instantiates a new Tenant not found exception.
     *
     * @param tenantId  the tenant id
     * @param throwable the throwable
     */
    public TenantNotFoundException(String tenantId, Throwable throwable) {
        super("Could not find the tenant .", throwable);
        this.tenantId = tenantId;
    }

    /**
     * Instantiates a new Tenant not found exception.
     *
     * @param throwable the throwable
     */
    public TenantNotFoundException(Throwable throwable) {
        super(throwable);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " for tenant id :" + tenantId;
    }

    /**
     * Gets tenant id.
     *
     * @return the tenant id
     */
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
