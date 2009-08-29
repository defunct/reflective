package com.goodworkalan.reflective;

import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ACCESS;
import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ARGUMENT;
import static com.goodworkalan.reflective.ReflectiveException.INVOCATION_TARGET;

import java.lang.reflect.InvocationTargetException;

/**
 * A wrapper around a <code>java.lang.reflect.Method</code> that gathers the
 * different possible exceptions thrown and wraps them in a single exception for
 * sane exception handling.
 * 
 * @author Alan Gutierrez
 */
public class Method {
    /** The underlying java.lang.reflect.Method. */
    private final java.lang.reflect.Method method;

    /**
     * Create a method wrapper around the given java.lang.reflect.Method.
     * 
     * @param method
     *            The native method.
     */
    public Method(java.lang.reflect.Method method) {
        this.method = method;
    }

    /**
     * Get the underlying java.lang.reflect.Method.
     * 
     * @return The underlying method.
     */
    public java.lang.reflect.Method getNative() {
        return method;
    }

    /**
     * Invoke the underlying method against the given object with the given
     * arguments. See <code>java.lang.reflect.Method.invoke</code> for the
     * possible exceptions thrown from this method.
     * 
     * @param obj
     *            The target object.
     * @param args
     *            The method arguments.
     * @return The result of the method invocation.
     * @throws ReflectiveException
     *             If the method is invoked with an illegal argument, if the
     *             method is not accessible to the caller, or if the method
     *             invoked raises an exception.
     * 
     */
    public Object invoke(Object obj, Object...args) throws ReflectiveException {
        try {
            return method.invoke(obj, args);
        } catch (IllegalArgumentException e) {
            throw new ReflectiveException(ILLEGAL_ARGUMENT, e);
        } catch (IllegalAccessException e) {
            throw new ReflectiveException(ILLEGAL_ACCESS, e);
        } catch (InvocationTargetException e) {
            throw new ReflectiveException(INVOCATION_TARGET, e);
        }
    }

    /**
     * A method wrapper is equal if the given object is also a method wrapper
     * and the underlying method objects are equal.
     * 
     * @param object
     *            The object to test for equality to this object.
     * @return True if the object is also a method wrapper around the name
     *         method.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Method) {
            Method method = (Method) object;
            return this.method.equals(method.method);
        }
        return false;
    }

    /**
     * The hash code is the hash code of the underlying method.
     * 
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return method.hashCode();
    }
}
