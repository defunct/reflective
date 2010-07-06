package com.goodworkalan.reflective.setter;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/**
 * A setter that writes a field value.
 *
 * @author Alan Gutierrez
 */
public class FieldSetter implements Setter {
    /** The field. */
    private final Field field;

    /**
     * Create a field setter using the given field.
     * 
     * @param field
     *            The field.
     */
    public FieldSetter(Field field) {
        this.field = field;
    }

    /**
     * Set the field associated with this setter in the given object to the
     * given value.
     * 
     * @param object
     *            The object.
     * @param value
     *            The value.
     * @throws IllegalAccessException
     *             If the class or method is not visible.
     * @exception IllegalArgumentException
     *                If the value cannot be assigned to the field.
     */
    public void set(final Object object, final Object value)
    throws IllegalArgumentException, IllegalAccessException  {
        field.set(object, value);
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
