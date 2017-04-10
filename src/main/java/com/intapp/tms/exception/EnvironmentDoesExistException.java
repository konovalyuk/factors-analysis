package com.intapp.tms.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The type Environment does exist exception.
 */
@RequiredArgsConstructor
@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Environment does exist")
public class EnvironmentDoesExistException extends RuntimeException {
}
