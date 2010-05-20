package com.goodworkalan.reflective.setter;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

import com.goodworkalan.reflective.Reflective;
import com.goodworkalan.reflective.ReflectiveException;

/**
 * Sets an object property using a single object void method.
 * <p>
 * FIXME Move to reflective.
 * 
 * @author Alan Gutierrez
 */
public class MethodSetter implements Setter {
    /** The single argument void method. */
    private final Method method;

    /**
     * Create a method setter that uses the given single argument void method to
     * set an object property.
     * 
     * @method The setter method.
     */
    public MethodSetter(Method method) {
        this.method = method;
    }

    /**
     * Set the property associated with this setter in the given object to the
     * given value.
     * 
     * @param object
     *            The object.
     * @param value
     *            The property value.
     */
    public void set(final Object object, final Object value) throws ReflectiveException {
        try {
            method.invoke(object, value);
        } catch (Throwable e) {
            throw new ReflectiveException(Reflective.encode(e), e);
        }
    }
    
    /**
     * Return the underlying Java reflection method.
     * 
     * @return The underlying method.
     */
    public Member getNative() {
        return method;
    }
    
    /**
     * Get the property type.
     * 
     * @return The property type.
     */
    public Class<?> getType() {
        return method.getParameterTypes()[0];
    }
}
