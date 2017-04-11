package com.intapp.tms.service.exception;


/**
 * Spring's database support defines this exception.
 *
 * @author Copied from Spring
 */
public class DataIntegrityViolationException extends DataAccessException {

    /**
     * Unique ID for Serialized object
     */
    private static final long serialVersionUID = 3179085597460057409L;


    /**
     * Instantiates a new Data integrity violation exception.
     *
     * @param msg the msg
     */
    public DataIntegrityViolationException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new Data integrity violation exception.
     *
     * @param msg the msg
     * @param t   the t
     */
    public DataIntegrityViolationException(String msg, Throwable t) {
        super(msg, t);
    }

}
