package com.goodworkalan.reflective;

import java.lang.reflect.InvocationTargetException;

public class Reflective {
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

    /** The static initialization threw an exception. */
    public static final int STATIC_INITIALIZER = 205;
    
    /** The constructor or method is protected by the current security policy. */
    public final static int SECURITY = 101;

    /** There is no such constructor or method. */
    public final static int NO_SUCH_METHOD = 102;

    /**
     * Get the error code for the given exception or else rethrow the exception
     * if it is not a reflection exception.
     */
    public static int encode(Throwable e) {
        if (e instanceof ExceptionInInitializerError) {
            return STATIC_INITIALIZER;
        } else if (e instanceof Error) {
            throw (Error) e;
        }
        return encode((Exception) e);
    } 
    
    public static int encode(Exception e) {
        if (e instanceof SecurityException) {
            return SECURITY;
        } else if (e instanceof NoSuchMethodException) {
            return NO_SUCH_METHOD;
        } else if (e instanceof NoSuchFieldException) {
            return NO_SUCH_METHOD;
        } else if (e instanceof IllegalArgumentException) {
            return ILLEGAL_ARGUMENT;
        } else if (e instanceof InstantiationException) {
            return INSTANCIATION;
        } else if (e instanceof IllegalAccessException) {
            return ILLEGAL_ACCESS;
        } else if (e instanceof InvocationTargetException) {
            return INVOCATION_TARGET;
        } else if (e instanceof RuntimeException) {
            throw (RuntimeException) e;
        } else {
            throw new RuntimeException(e);
        }
    }
}
