package com.goodworkalan.reflective;

import java.lang.reflect.InvocationTargetException;

/**
 * Remainder of the second incarnation of Reflective, using callbacks,
 * so that testing raised exceptions was especially easy.
 * 
 * @author Alan Gutierrez
 *
 * @param <T> The return type of the reflection.
 */
public interface Reflection<T> {
    /**
     * Invoke a reflective operation.
     * 
     * @return The result of the reflection.
     * @throws InstantiationException
     *             If the class is abstract, or for the default constructor, if
     *             the class is an interface, array, or primitive.
     * @throws IllegalAccessException
     *             If the class or member is inaccessible.
     * @throws InvocationTargetException
     *             If the member raises an exception.
     * @throws NoSuchFieldException
     *             If there is no such field.
     * @throws NoSuchMethodException
     *             If there is no such method.
     */
    public T reflect()
    throws InstantiationException,
           IllegalAccessException,
           InvocationTargetException,
           NoSuchFieldException,
           NoSuchMethodException;
}
