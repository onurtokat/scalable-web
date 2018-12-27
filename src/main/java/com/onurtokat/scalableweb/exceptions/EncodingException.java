package com.onurtokat.scalableweb.exceptions;

/**
 * EncodingException class is thrown when data decoding operation
 * encounters an exception
 *
 * @author onurtokat
 */
public class EncodingException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *EncodingException method gives message when decoding operation
     * encounters an exception
     *
     * @param message Exception message to be clear about exception
     */
    public EncodingException(String message) {
        super(message);
    }

    /**
     *EncodingException method gives message when decoding operation
     * encounters an exception
     *
     * @param message Exception message to be clear about exception
     * @param cause cause default java API exception stack trace stream
     */
    public EncodingException(String message, Throwable cause) {
        super(message, cause);
    }
}
