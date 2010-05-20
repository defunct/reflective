package com.goodworkalan.reflective.setter;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

import com.goodworkalan.reflective.Reflective;
import com.goodworkalan.reflective.ReflectiveException;


public class FieldSetter implements Setter {
    private final Field field;
    
    public FieldSetter(Field field) {
        this.field = field;
    }
    
    /**
     * Set the field associated with this setter in the given object to the given value.
     */
    public void set(final Object object, final Object value) throws ReflectiveException {
        try {
            field.set(object, value);
        } catch (Throwable e) {
            throw new ReflectiveException(Reflective.encode(e), e);
        }
    }
    
    /**
     * Return the underlying Java reflection field.
     * 
     * @return The underlying field.
     */
    public Member getNative() {
        return field;
    }

    /**
     * Get the property type.
     * 
     * @return The property type.
     */
    public Class<?> getType() {
        return field.getType();
    }
}
