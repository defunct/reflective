package com.goodworkalan.reflective;

import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ACCESS;
import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ARGUMENT;
import static com.goodworkalan.reflective.ReflectiveException.INSTANCIATION;
import static com.goodworkalan.reflective.ReflectiveException.INVOCATION_TARGET;

import java.lang.reflect.InvocationTargetException;

public class Constructor<T> {
    private final java.lang.reflect.Constructor<T> constructor;
    
    public Constructor(java.lang.reflect.Constructor<T> constructor) {
        this.constructor = constructor;
    }
    
    public java.lang.reflect.Constructor<T> getNative() {
        return constructor;
    }

    public T newInstance(Object...initargs) throws ReflectiveException {
        try {
            return constructor.newInstance(initargs);
        } catch (IllegalArgumentException e) {
            throw new ReflectiveException(ILLEGAL_ARGUMENT, e);
        } catch (InstantiationException e) {
            throw new ReflectiveException(INSTANCIATION, e);
        } catch (IllegalAccessException e) {
            throw new ReflectiveException(ILLEGAL_ACCESS, e);
        } catch (InvocationTargetException e) {
            throw new ReflectiveException(INVOCATION_TARGET, e);
        }
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Constructor<?>) {
            Constructor<?> constructor = (Constructor<?>) object;
            return this.constructor.equals(constructor.constructor);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return constructor.hashCode();
    }
}
