package com.onurtokat.scalableweb.exceptions;

/**
 * PrettyJsonFormatException is thrown when Gson pretty format
 * returns an Exception
 *
 * @author onurtokat
 */
public class PrettyJsonFormatException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *PrettyJsonFormatException method gives message when Gson pretty
     * format returns an Exception
     *
     * @param message Exception message to be clear about exception
     */
    public PrettyJsonFormatException(String message) {
        super(message);
    }

    /**
     *PrettyJsonFormatException method gives message when Gson pretty
     * format returns an Exception
     *
     * @param message Exception message to be clear about exception
     * @param cause cause default java API exception stack trace stream
     */
    public PrettyJsonFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
