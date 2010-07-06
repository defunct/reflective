package com.goodworkalan.reflective.getter;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/**
 * Get a public property from and object instance. This interface makes property
 * access identical for code that wants to tread public fields and public getter
 * methods as object properties.
 * 
 * @author Alan Gutierrez
 */
public interface Getter {
    /**
     * Get the value using the getter method from the given object.
     * 
     * @param object
     *            The object.
     * @return The value obtained from the getter method.
     * @throws ReflectiveException
     *             If an exception occurs during reflection.
     */
    public Object get(Object object) throws IllegalAccessException, InvocationTargetException;

    /**
     * Get the property name.
     * 
     * @return The property name.
     */
    public String getName();
    
    /**
     * Get the property type.
     * 
     * @return The property type.
     */
    public Class<?> getType();

    /**
     * Get the generic property type.
     * 
     * @return The generic property type.
     */
    public Type getGenericType();
    
    /**
     * Get the underlying member.
     * 
     * @return The member.
     */
    public Member getMember();

    /**
     * Get the underlying accessible object.
     * 
     * @return The accessible object.
     */
    public AccessibleObject getAccessibleObject();
}
