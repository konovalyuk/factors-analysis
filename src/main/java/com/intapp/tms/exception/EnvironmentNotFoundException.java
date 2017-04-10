package com.intapp.tms.exception;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Environment not found exception.
 */
@RequiredArgsConstructor
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Environment not found")
public class EnvironmentNotFoundException extends RuntimeException {
}

