package com.goodworkalan.reflective;

import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ACCESS;
import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ARGUMENT;

/**
 * A wrapper around a <code>java.lang.reflect.Field</code> that gathers the
 * different possible exceptions thrown and wraps them in a single exception for
 * sane exception handling.
 * 
 * @author Alan Gutierrez
 */
public class Field {
    /** The underlying native field definition. */
    private final java.lang.reflect.Field field;

    /**
     * Create a constructor wrapper around the given
     * <code>java.lang.reflect.Field</code>.
     * 
     * @param costructor
     *            The native field.
     */
    public Field(java.lang.reflect.Field field) {
        this.field = field;
    }

    /**
     * Sets the field represented by this object to the given value in the given
     * object. This method calls the set method of the wrapped
     * {@link java.lang.reflect.Field} instance, catches
     * {@link IllegalArgumentException} and {@link IllegalAccessException}
     * instances thrown and wraps them in a {@link ReflectiveException}.
     * 
     * @param object
     *            The object field to set or null if the field is static.
     * @param value
     *            The value to set.
     * @throws ReflectiveException
     *             For illegal argument or illegal access exceptions thrown 
     *             during assignment.
     */
    public void set(Object object, Object value) throws ReflectiveException {
        try {
            field.set(object, value);
        } catch (IllegalArgumentException e) {
            throw new ReflectiveException(ILLEGAL_ARGUMENT, e);
        } catch (IllegalAccessException e) {
            throw new ReflectiveException(ILLEGAL_ACCESS, e);
        }
    }

    /**
     * Get the value of field represented by this field object in the given
     * object. This method calls the get method of the wrapped
     * {@link java.lang.reflect.Field} instance, catches
     * {@link IllegalArgumentException} and {@link IllegalAccessException}
     * instances thrown and wraps them in a {@link ReflectiveException}.
     * 
     * @param object
     *            The object field to set or null if the field is static.
     * @throws ReflectiveException
     *             For illegal argument or illegal access exceptions thrown
     *             during assignment.
     */
    public Object get(Object object) throws ReflectiveException {
        try {
            return field.get(object);
        } catch (IllegalArgumentException e) {
            throw new ReflectiveException(ILLEGAL_ARGUMENT, e);
        } catch (IllegalAccessException e) {
            throw new ReflectiveException(ILLEGAL_ACCESS, e);
        }
    }

    /**
     * Get the underlying native field definition.
     * 
     * @return The underlying native field definition.
     */
    public java.lang.reflect.Field getNative() {
        return field;
    }

    /**
     * A field wrapper is equal if the given object is also a field wrapper and
     * the underlying field objects are equal.
     * 
     * @param object
     *            The object to test for equality to this object.
     * @return True if the object is also a field wrapper around the same field.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Field) {
            return ((Field) object).field.equals(field);
        }
        return false;
    }

    /**
     * The hash code is the hash code of the underlying field.
     * 
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return field.hashCode();
    }
}
