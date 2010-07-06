package com.goodworkalan.reflective.getter;

import static org.testng.Assert.assertEquals;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

import org.testng.annotations.Test;

/**
 * Unit tests for the {@link FieldGetter} class.
 *
 * @author Alan Gutierrez
 */
public class FieldGetterTest {
    /** Test everything about the field getter. */
    @Test
    public void everything() throws IntrospectionException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchFieldException {
        Widget widget = new Widget();
        widget.one = "One";
        widget.setTwo("Two");
        FieldGetter fieldGetter = new FieldGetter(Widget.class.getField("one"));
        assertEquals(fieldGetter.get(widget), "One");
        assertEquals(fieldGetter.getName(), "one");
        assertEquals(fieldGetter.getType(), String.class);
    }
}
