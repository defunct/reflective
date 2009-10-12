package com.goodworkalan.reflective;

import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ACCESS;
import static com.goodworkalan.reflective.ReflectiveException.ILLEGAL_ARGUMENT;

public class Field {
    private final java.lang.reflect.Field field;

    public Field(java.lang.reflect.Field field) {
        this.field = field;
    }

    public void set(Object object, Object value) throws ReflectiveException {
        try {
            field.set(object, value);
        } catch (IllegalArgumentException e) {
            throw new ReflectiveException(ILLEGAL_ARGUMENT, e);
        } catch (IllegalAccessException e) {
            throw new ReflectiveException(ILLEGAL_ACCESS, e);
        }
    }

    public Object get(Object object) throws ReflectiveException {
        try {
            return field.get(object);
        } catch (IllegalArgumentException e) {
            throw new ReflectiveException(ILLEGAL_ARGUMENT, e);
        } catch (IllegalAccessException e) {
            throw new ReflectiveException(ILLEGAL_ACCESS, e);
        }
    }

    public java.lang.reflect.Field getNative() {
        return field;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Field) {
            return ((Field) object).field.equals(field);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return field.hashCode();
    }
}
