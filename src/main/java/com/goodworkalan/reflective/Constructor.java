package com.goodworkalan.reflective;

import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ACCESS;
import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ARGUMENT;
import static com.goodworkalan.reflective.ReflectiveException.INSTANCIATION;
import static com.goodworkalan.reflective.ReflectiveException.INVOCATION_TARGET;

import java.lang.reflect.InvocationTargetException;

/**
 * A wrapper around a <code>java.lang.reflect.Constructor</code> that gathers
 * the different possible exceptions thrown and wraps them in a single exception
 * for sane exception handling.
 * 
 * @author Alan Gutierrez
 */
public class Constructor<T> {
    /** The underlying java.lang.reflect.Constructor. */
    private final java.lang.reflect.Constructor<T> constructor;

    /**
     * Create a constructor wrapper around the given
     * java.lang.reflect.Constructor.
     * 
     * @param costructor
     *            The native method.
     */
    public Constructor(java.lang.reflect.Constructor<T> constructor) {
        this.constructor = constructor;
    }

    /**
     * Get the underlying java.lang.reflect.Constructor.
     * 
     * @return The underlying constructor.
     */
    public java.lang.reflect.Constructor<T> getNative() {
        return constructor;
    }

    /**
     * Create a new instance by calling the underlying constructor with the
     * given arguments.
     * 
     * @param obj
     *            The target object.
     * @param args
     *            The method arguments.
     * @return The result of the method invocation.
     * @throws ReflectiveException
     *             If the constructor is invoked with an illegal argument, if
     *             the class is abstract or an instance, if the constructor is
     *             not accessible to the caller, or if the constructor raises an
     *             exception.
     */
    public T newInstance(Object... initargs) throws ReflectiveException {
        try {
            return constructor.newInstance(initargs);
        } catch (IllegalArgumentException e) {
            throw new ReflectiveException(ILLEGAL_ARGUMENT, e);
        } catch (InstantiationException e) {
            throw new ReflectiveException(INSTANCIATION, e);
        } catch (IllegalAccessException e) {
            throw new ReflectiveException(ILLEGAL_ACCESS, e);
        } catch (InvocationTargetException e) {
            throw new ReflectiveException(INVOCATION_TARGET, e);
        }
    }

    /**
     * A constructor wrapper is equal if the given object is also a constructor
     * wrapper and the underlying constructor objects are equal.
     * 
     * @param object
     *            The object to test for equality to this object.
     * @return True if the object is also a constructor wrapper around the name
     *         constructor.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Constructor<?>) {
            Constructor<?> constructor = (Constructor<?>) object;
            return this.constructor.equals(constructor.constructor);
        }
        return false;
    }

    /**
     * The hash code is the hash code of the underlying constructor.
     * 
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return constructor.hashCode();
    }
}
