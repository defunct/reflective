package com.goodworkalan.reflective.getter;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;

import com.goodworkalan.reflective.Method;
import com.goodworkalan.reflective.ReflectiveException;

/**
 * A proeprty getter that reads a getter method.
 *
 * @author Alan Gutierrez
 */
public class MethodGetter implements Getter {
    /** The getter method. */
    private final Method method;

    /** The property name. */
    private final String name;
    
    /**
     * Create a new method getter with the given name using the given no
     * argument getter method.
     * 
     * @param method
     *            The getter method.
     * @param name
     *            The property name.
     */
    public MethodGetter(Method method, String name) {
        this.method = method;
        this.name = name;
    }

    /**
     * Get the value using the getter method from the given object.
     * 
     * @param object
     *            The object.
     * @return The value obtained from the getter method.
     * @throws ReflectiveException
     *             If an exception occurs during reflection.
     */
    public Object get(Object object) throws ReflectiveException {
        return method.invoke(object);
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
        return method.getNative().getReturnType();
    }
    
    /**
     * Get the method as a member.
     * 
     * @return The member.
     */
    public Member getMember() {
        return method.getNative();
    }

    /**
     * Get the method as an accessible object.
     * 
     * @return The accessible object.
     */
    public AccessibleObject getAccessibleObject() {
        return method.getNative();
    }
}