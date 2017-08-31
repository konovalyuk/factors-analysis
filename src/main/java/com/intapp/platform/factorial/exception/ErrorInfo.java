package com.intapp.platform.factorial.exception;

/**
 * Created by maksymk on 4/10/2017.
 */
public class ErrorInfo {

    /**
     * The Url.
     */
    public final String url;
    /**
     * The Exception.
     */
    public final String exception;

    /**
     * Instantiates a new Error info.
     *
     * @param url       the url
     * @param exception the exception
     */
    public ErrorInfo(String url, Exception exception) {
        this.url = url;
        this.exception = exception.getMessage();
    }

}
