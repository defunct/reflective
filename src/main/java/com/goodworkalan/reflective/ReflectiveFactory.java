package com.goodworkalan.reflective;

import static com.goodworkalan.reflective.ReflectiveException.NO_SUCH_METHOD;
import static com.goodworkalan.reflective.ReflectiveException.SECURITY;

public class ReflectiveFactory {
    public <T> com.goodworkalan.reflective.Constructor<T> getConstructor(Class<T> type, Class<?>... initargs)
    throws ReflectiveException {
        try {
            return new Constructor<T>(type.getConstructor(initargs));
        } catch (SecurityException e) {
            throw new ReflectiveException(SECURITY, e);
        } catch (NoSuchMethodException e) {
            throw new ReflectiveException(NO_SUCH_METHOD, e);
        }
    }

    public <T> Method getMethod(Class<T> type, String name, Class<?>... parameterTypes)
    throws ReflectiveException {
        try {
            return new Method(type.getMethod(name, parameterTypes));
        } catch (SecurityException e) {
            throw new ReflectiveException(SECURITY, e);
        } catch (NoSuchMethodException e) {
            throw new ReflectiveException(NO_SUCH_METHOD, e);
        }
    }
}