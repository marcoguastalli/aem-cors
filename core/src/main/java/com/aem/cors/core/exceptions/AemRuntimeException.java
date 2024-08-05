package com.aem.cors.core.exceptions;

/**
 * Generic Runtime Exception
 */
public class AemRuntimeException extends RuntimeException {

    public AemRuntimeException(String message) {
        super(message);
    }

    public AemRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}