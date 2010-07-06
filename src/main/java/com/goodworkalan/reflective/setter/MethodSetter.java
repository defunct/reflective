package com.goodworkalan.reflective.setter;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Sets an object property using a single object void method.
 * 
 * @author Alan Gutierrez
 */
public class MethodSetter implements Setter {
    /** The single argument void method. */
    private final Method method;

    /** The property name. */
    private final String name;

    /**
     * Create a method setter that uses the given single argument void method to
     * set an object property.
     * 
     * @param method
     *            The setter method.
     * @param name
     *            The setter name.
     */
    public MethodSetter(Method method, String name) {
        this.method = method;
        this.name = name;
    }

    /**
     * Set the property associated with this setter in the given object to the
     * given value.
     * 
     * @param object
     *            The object.
     * @param value
     *            The property value.
     * @throws InvocationTargetException
     *             If the setter throws an exception.
     * @throws IllegalAccessException
     *             If the class or method is not visible.
     * @exception IllegalArgumentException
     *                If the value cannot be assigned to the field.
     */
    public void set(final Object object, final Object value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        method.invoke(object, value);
    }

    /**
     * Get the property name.
     * 
     * @return The property name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the property type.
     * 
     * @return The property type.
     */
    public Class<?> getType() {
        return method.getParameterTypes()[0];
    }
    
    /**
     * Get the generic property type.
     * 
     * @return The generic property type.
     */
    public Type getGenericType() {
        return method.getGenericParameterTypes()[0];
    }
    
    /**
     * Get the method as a member.
     * 
     * @return The member.
     */
    public Member getMember() {
        return method;
    }

    /**
     * Get the method as an accessible object.
     * 
     * @return The accessible object.
     */
    public AccessibleObject getAccessibleObject() {
        return method;
    }
}
