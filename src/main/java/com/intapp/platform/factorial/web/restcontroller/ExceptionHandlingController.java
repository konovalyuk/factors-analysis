package com.intapp.platform.factorial.web.restcontroller;

import com.intapp.platform.factorial.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type Exception handling controller.
 */
@ControllerAdvice
public class ExceptionHandlingController {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

    /**
     * Handle bad request error info.
     *
     * @param req the req
     * @param ex  the ex
     * @return the error info
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            JsonParsingException.class,
    })
    @ResponseBody
    public ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {
        logger.error("Custom exception : " + ex);
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }

/*         Validator         */

    /**
     * Handle map.
     *
     * @param exception the exception
     * @return the map
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handle(MethodArgumentNotValidException exception) {
        logger.error("MethodArgumentNotValidException : " + exception);
        return error(exception.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList()));
    }


    /**
     * Handle map.
     *
     * @param exception the exception
     * @return the map
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handle(ConstraintViolationException exception) {
        logger.error("ConstraintViolationException : " + exception);
        return error(exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()));
    }

    private Map error(Object message) {
        return Collections.singletonMap("error", message);
    }
    /*      end   Validator         */

    /**
     * Handle exception model and view.
     *
     * @param request   the request
     * @param exception the exception
     * @return the model and view
     * @throws Exception the exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public GlobalErrorInfo handleException(HttpServletRequest request, Exception exception) throws Exception {
        if (AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null) {
            throw exception;
        }
        logger.error(" Global Exception handler : " + exception);

        return new GlobalErrorInfo(request.getRequestURI().toString(), exception.getMessage(), exception.getStackTrace());
    }

}
