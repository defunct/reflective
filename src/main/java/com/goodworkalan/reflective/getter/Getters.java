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

/**
 * Utility class to obtain the getter members of classes.
 * 
 * @author Alan Gutierrez
 */
public class Getters {
    /** The cache of classes to their maps of getters. */
    private final static ConcurrentMap<Class<?>, Map<String, Getter>> GETTERS = new ConcurrentHashMap<Class<?>, Map<String, Getter>>();
    
    /**
     * Get the bean properties for the given bean class. This method is
     * extracted in order to test introspection exception handling.
     * 
     * @param beanClass
     *            The bean class.
     * @param flags
     *            The flags to pass to the introspector.
     * @return The bean information.
     */
    static BeanInfo introspect(Class<?> beanClass, int flags) {
        try {
            return Introspector.getBeanInfo(beanClass, flags);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get a map of the getters indexed on property name for the public
     * properties and fields exposed by the given bean class.
     * 
     * @param beanClass
     *            The bean class.
     * @return A list of getters for the public properties and fields of the
     *         class.
     */
    public static Map<String, Getter> getGetters(Class<?> type) {
        Map<String, Getter> getters = GETTERS.get(type);
        if (getters == null) {
            getters = new LinkedHashMap<String, Getter>();
            BeanInfo beanInfo = introspect(type, Introspector.USE_ALL_BEANINFO);
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
