package com.goodworkalan.reflective;

/**
 * A wrapper around any one of the many exceptions thrown by Java reflection
 * from the flattened exception hierarchy of Java reflection exceptions. The
 * exception makes it possible to write terse and targeted exception handling
 * blocks when invoking a constructor or method through reflection.
 * 
 * @author Alan Gutierrez
 */
public class ReflectiveException extends Exception {
    /** Serial version id. */
    private static final long serialVersionUID = 1L;

    /**
     * The group of error codes for exception raised when a constructor or
     * method is inaccessible or cannot be found.
     */
    public static final int CANNOT_FIND = 1;

    /**
     * The group of error code for exceptions raised when a constructor or
     * method invocation fails.
     */
    public static final int CANNOT_EXECUTE = 2;

    /** An illegal argument was passed to a constructor or method. */
    public static final int ILLEGAL_ARGUMENT = 201;

    /** A constructor failed because the class is abstract or an interface. */
    public static final int INSTANCIATION = 202;

    /** The constructor or method is not visible to the caller. */
    public static final int ILLEGAL_ACCESS = 203;

    /** The constructor or method threw an exception. */
    public static final int INVOCATION_TARGET = 204;

    /** The constructor or method is protected by the current security policy. */
    public final static int SECURITY = 101;

    /** There is no such constructor or method. */
    public final static int NO_SUCH_METHOD = 102;

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
    public ReflectiveException(int code, Throwable cause) {
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
