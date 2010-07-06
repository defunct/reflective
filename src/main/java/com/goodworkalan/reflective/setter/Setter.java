package com.goodworkalan.reflective.setter;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/**
 * Set a public property in an object instance. This interface makes property
 * access identical for code that wants to tread public fields and public setter
 * methods as object properties.
 * 
 * @author Alan Gutierrez
 */
public interface Setter {
    /**
     * Set the value using the setter method from the given object.
     * 
     * @param object
     *            The object.
     * @param value
     *            The value.
     * @throws InvocationTargetException
     *             If the setter throws an exception.
     * @throws IllegalAccessException
     *             If the class or method is not visible.
     * @exception IllegalArgumentException
     *                If the value cannot be assigned to the field.
     */
    public void set(Object object, Object value) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException;

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
    public AccessibleObject getAccessibleObject();}