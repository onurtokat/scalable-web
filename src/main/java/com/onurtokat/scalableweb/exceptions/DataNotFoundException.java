package com.onurtokat.scalableweb.exceptions;

/**
 * DataNotFoundException is thrown when no data exists for
 * given id
 *
 * @author onurtokat
 */
public class DataNotFoundException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *DataNotFoundException method gives message when no data exists for
     * given id
     *
     * @param message Exception message to be clear about exception
     */
    public DataNotFoundException(String message) {
        super(message);
    }

    /**
     *DataNotFoundException method gives message when no data exists for
     * given id
     *
     * @param message Exception message to be clear about exception
     * @param cause cause default java API exception stack trace stream
     */
    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
