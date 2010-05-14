package com.goodworkalan.reflective.setter;

import java.lang.reflect.Member;

import com.goodworkalan.reflective.ReflectiveException;

public interface Setter {
    public void set(Object object, Object value) throws ReflectiveException;
    
    public Member getNative();
    
    public Class<?> getType();
}
