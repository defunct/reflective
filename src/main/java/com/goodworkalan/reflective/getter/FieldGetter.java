package com.goodworkalan.reflective.getter;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/**
 * A getter that reads a field value.
 *
 * @author Alan Gutierrez
 */
public class FieldGetter implements Getter {
    /** The field. */
    private final Field field;

    /**
     * Create a field getter from the given field.
     * 
     * @param field
     *            The field.
     */
    public FieldGetter(Field field) {
        this.field = field;
    }

    /**
     * Get the field value from the given object.
     * 
     * @param object
     *            The object.
     * @return The field value.
     * @throws ReflectiveException
     *             If an exception is thrown during reflection.
     */
    public Object get(final Object object) throws IllegalAccessException {
        return field.get(object);
    }

    /**
     * Get the field name.
     * 
     * @return The field name.
     */
    public String getName() {
        return field.getName();
    }
    
    /**
     * Get the field type.
     * 
     * @return The field type.
     */
    public Class<?> getType() {
        return field.getType();
    }
    
    /**
     * Get the generic field type.
     * 
     * @return The generic field type.
     */
    public Type getGenericType() {
        return field.getGenericType();
    }
    
    /**
     * Get the field as a member.
     * 
     * @return The member.
     */
    public Member getMember() {
        return field;
    }

    /**
     * Get the field as an accessible object.
     * 
     * @return The accessible object.
     */
    public AccessibleObject getAccessibleObject() {
        return field;
    }
}