package com.onurtokat.scalableweb.exceptions;

/**
 * MatchedDataDoesNotFoundException class throws exception
 * when no data exists to compare
 *
 * @author onurtokat
 */
public class MatchedDataDoesNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *MatchedDataDoesNotFoundException method gives message when No data
     * exists to compare
     *
     * @param message Exception message to be clear about exception
     */
    public MatchedDataDoesNotFoundException(String message) {
        super(message);
    }

    /**
     * MatchedDataDoesNotFoundException method gives message when No data
     * exists to compare
     *
     * @param message Exception message to be clear about exception
     * @param cause cause default java API exception stack trace stream
     */
    public MatchedDataDoesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
