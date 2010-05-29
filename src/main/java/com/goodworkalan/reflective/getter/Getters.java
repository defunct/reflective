package com.goodworkalan.reflective.getter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Getters {
    /** The cache of classes to their list of getters. */
    private final static ConcurrentMap<Class<?>, Map<String, Getter>> GETTERS = new ConcurrentHashMap<Class<?>, Map<String, Getter>>();
    
    static BeanInfo introspect(Class<?> beanClass, Class<?> stopClass) {
        try {
            return Introspector.getBeanInfo(beanClass, stopClass);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Getter> getGetters(Class<?> type) {
        Map<String, Getter> getters = GETTERS.get(type);
        if (getters == null) {
            getters = new LinkedHashMap<String, Getter>();
            BeanInfo beanInfo = introspect(type, null);
            for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                java.lang.reflect.Method read = descriptor.getReadMethod();
                if (read != null) {
                    String name = descriptor.getName();
                    getters.put(name, new MethodGetter(read, name));
                }
            }
            for (java.lang.reflect.Field field : type.getFields()) {
                getters.put(field.getName(), new FieldGetter(field));
            }
            GETTERS.put(type, Collections.unmodifiableMap(getters));
            getters = GETTERS.get(type);
        }
        return getters;
    }
}
