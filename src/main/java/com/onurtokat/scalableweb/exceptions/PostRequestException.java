package com.onurtokat.scalableweb.exceptions;

public class PostRequestException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *PostRequestException method gives message when No data
     * exists to compare
     *
     * @param message Exception message to be clear about exception
     */
    public PostRequestException(String message) {
        super(message);
    }

    /**
     * PostRequestException method gives message when No data
     * exists to compare
     *
     * @param message Exception message to be clear about exception
     * @param cause cause default java API exception stack trace stream
     */
    public PostRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
