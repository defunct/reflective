package com.goodworkalan.reflective;

/**
 * A wrapper around any one of the many exceptions thrown by Java reflection
 * from the flattened exception hierarchy of Java reflection exceptions. The
 * exception makes it possible to write terse and targeted exception handling
 * blocks when invoking a constructor or method through reflection.
 * 
 * @author Alan Gutierrez
 */
public class ReflectionException extends Exception {
    /** Serial version id. */
    private static final long serialVersionUID = 1L;

    /** The error code. */
    private final int code;

    /**
     * Create a reflective exception with the given error code and cause.
     * 
     * @param code
     *            The error code.
     * @param cause
     *            The cause.
     */
    public ReflectionException(int code, Throwable cause) {
        super(cause.getMessage(), cause);
        this.code = code;
    }

    /**
     * Get the error code.
     * 
     * @return The error code.
     */
    public int getCode() {
        return code;
    }
}
