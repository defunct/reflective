package com.goodworkalan.reflective;

import static com.goodworkalan.reflective.ReflectiveException.NO_SUCH_METHOD;
import static com.goodworkalan.reflective.ReflectiveException.SECURITY;

/**
 * A factory to create constructor and method wrappers that you can override in
 * testing to raise exceptions.
 * 
 * @author Alan Gutierrez
 */
public class ReflectiveFactory {
    /**
     * Create a new constructor wrapper for the constructor of the given class
     * with the given initialization arguments.
     * 
     * @param <T>
     *            The type of object to construct.
     * @param type
     *            The class.
     * @param initargs
     *            The constructor argument types.
     * @return A wrapper around a constructor.
     * @throws ReflectiveException
     *             If the method is not found or if the constructor is
     *             inaccessible do to a security policy.
     */
    public <T> Constructor<T> getConstructor(Class<T> type, Class<?>... initargs)
    throws ReflectiveException {
        try {
            return new Constructor<T>(type.getConstructor(initargs));
        } catch (SecurityException e) {
            throw new ReflectiveException(SECURITY, e);
        } catch (NoSuchMethodException e) {
            throw new ReflectiveException(NO_SUCH_METHOD, e);
        }
    }

    /**
     * Create a new method wrapper for the method of the given class, with the
     * given name and the with the given initialization arguments.
     * 
     * @param <T>
     *            The type of class..
     * @param type
     *            The class.
     * @param name
     *            The name of the class.
     * @param parameterTypes
     *            The method argument types.
     * @return A wrapper around a constructor.
     * @throws ReflectiveException
     *             If the method is not found or if the constructor is
     *             inaccessible do to a security policy.
     */
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

    /**
     * Create a new field wrapper for the field of the given class, with the
     * given name.
     * 
     * @param <T>
     *            The type of class..
     * @param type
     *            The class.
     * @param name
     *            The name of the field.
     * @return A wrapper around a constructor.
     * @throws ReflectiveException
     *             If the method is not found or if the constructor is
     *             inaccessible do to a security policy.
     */
    public <T> Field getField(Class<T> type, String name) throws ReflectiveException {
        try {
            return new Field(type.getField(name));
        } catch (SecurityException e) {
            throw new ReflectiveException(SECURITY, e);
        } catch (NoSuchFieldException e) {
            throw new ReflectiveException(NO_SUCH_METHOD, e);
        }
    }
}