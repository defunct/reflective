package com.goodworkalan.reflective;

import java.lang.reflect.InvocationTargetException;

public interface Reflection<T> {
    public T reflect()
    throws IllegalArgumentException, InstantiationException,
           IllegalAccessException, InvocationTargetException,
           NoSuchFieldException, NoSuchMethodException;
}
