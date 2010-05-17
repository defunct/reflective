package com.goodworkalan.reflective;

import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ACCESS;
import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ARGUMENT;
import static com.goodworkalan.reflective.ReflectiveException.INSTANCIATION;
import static com.goodworkalan.reflective.ReflectiveException.INVOCATION_TARGET;
import static com.goodworkalan.reflective.ReflectiveException.NO_SUCH_METHOD;
import static com.goodworkalan.reflective.ReflectiveException.SECURITY;
import static com.goodworkalan.reflective.ReflectiveException.STATIC_INITIALIZER;

import java.lang.reflect.InvocationTargetException;

public class Reflective {
    public static <T> T reflect(Reflection<T> reflection) throws ReflectiveException {
        try {
            return reflection.reflect();
        } catch (SecurityException e) {
            throw new ReflectiveException(SECURITY, e);
        } catch (NoSuchMethodException e) {
            throw new ReflectiveException(NO_SUCH_METHOD, e);
        } catch (NoSuchFieldException e) {
            throw new ReflectiveException(NO_SUCH_METHOD, e);
        } catch (IllegalArgumentException e) {
            throw new ReflectiveException(ILLEGAL_ARGUMENT, e);
        } catch (InstantiationException e) {
            throw new ReflectiveException(INSTANCIATION, e);
        } catch (IllegalAccessException e) {
            throw new ReflectiveException(ILLEGAL_ACCESS, e);
        } catch (InvocationTargetException e) {
            throw new ReflectiveException(INVOCATION_TARGET, e);
        } catch (ExceptionInInitializerError e) {
            throw new ReflectiveException(STATIC_INITIALIZER, e);
        } 
    }
}
