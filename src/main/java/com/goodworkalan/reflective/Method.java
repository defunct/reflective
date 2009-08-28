package com.goodworkalan.reflective;

import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ACCESS;
import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ARGUMENT;
import static com.goodworkalan.reflective.ReflectiveException.INVOCATION_TARGET;

import java.lang.reflect.InvocationTargetException;

public class Method {
    private final java.lang.reflect.Method method;
    
    public Method(java.lang.reflect.Method method) {
        this.method = method;
    }
    
    public java.lang.reflect.Method getNative() {
        return method;
    }
    
    public Object invoke(Object obj, Object...args) throws ReflectiveException {
        try {
            return method.invoke(obj, args);
        } catch (IllegalArgumentException e) {
            throw new ReflectiveException(ILLEGAL_ARGUMENT, e);
        } catch (IllegalAccessException e) {
            throw new ReflectiveException(ILLEGAL_ACCESS, e);
        } catch (InvocationTargetException e) {
            throw new ReflectiveException(INVOCATION_TARGET, e);
        }
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Method) {
            Method method = (Method) object;
            return this.method.equals(method.method);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return method.hashCode();
    }
}
