package com.intapp.tms.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * The type Product does exist exception.
 */
@RequiredArgsConstructor
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Product does exist")
public class ProductDoesExistException extends RuntimeException {
}
