package com.goodworkalan.reflective.getter;

import static org.testng.Assert.assertEquals;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import org.testng.annotations.Test;

import com.goodworkalan.reflective.Method;
import com.goodworkalan.reflective.ReflectiveException;

/**
 * Unit tests for the {@link MethodGetter} class.
 *
 * @author Alan Gutierrez
 */
public class MethodGetterTest {
    /** Test everything about the method getter. */
    @Test
    public void everything() throws IntrospectionException, ReflectiveException {
        Widget widget = new Widget();
        widget.one = "One";
        widget.setTwo("Two");
        BeanInfo beanInfo = Introspector.getBeanInfo(Widget.class, Object.class);
        PropertyDescriptor property = beanInfo.getPropertyDescriptors()[0];
        MethodGetter methodGetter = new MethodGetter(new Method(property.getReadMethod()), property.getName());
        assertEquals(methodGetter.get(widget), "Two");
        assertEquals(methodGetter.getName(), "two");
        assertEquals(methodGetter.getType(), String.class);
    }
}
