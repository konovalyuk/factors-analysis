package com.intapp.tms.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Tenant is not active exception.
 */
@RequiredArgsConstructor
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Tenant is not active")
public class TenantIsNotActiveException extends RuntimeException {
}
