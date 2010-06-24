package com.goodworkalan.reflective;

import java.lang.reflect.InvocationTargetException;

// TODO Document.
public interface Reflection<T> {
    // TODO Document.
    public T reflect()
    throws InstantiationException,
           IllegalAccessException,
           InvocationTargetException,
           NoSuchFieldException,
           NoSuchMethodException;
}
