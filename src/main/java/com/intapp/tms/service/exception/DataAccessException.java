package com.intapp.tms.service.exception;

/**
 * Spring's database support defines this exception.
 *
 * @author Copied from Spring
 */
public class DataAccessException extends RuntimeException {

    /**
     * Unique ID for Serialized object
     */
    private static final long serialVersionUID = 4596120142430675432L;

    /**
     * Instantiates a new Data access exception.
     *
     * @param msg the msg
     */
    public DataAccessException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new Data access exception.
     *
     * @param msg the msg
     * @param t   the t
     */
    public DataAccessException(String msg, Throwable t) {
        super(msg, t);
    }

}

