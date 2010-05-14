package com.goodworkalan.reflective.getter;

import static org.testng.Assert.assertEquals;

import java.beans.IntrospectionException;

import org.testng.annotations.Test;

import com.goodworkalan.reflective.Field;
import com.goodworkalan.reflective.ReflectiveException;

/**
 * Unit tests for the {@link FieldGetter} class.
 *
 * @author Alan Gutierrez
 */
public class FieldGetterTest {
    /** Test everything about the field getter. */
    @Test
    public void everything() throws IntrospectionException, ReflectiveException, SecurityException, NoSuchFieldException {
        Widget widget = new Widget();
        widget.one = "One";
        widget.setTwo("Two");
        FieldGetter fieldGetter = new FieldGetter(new Field(Widget.class.getField("one")));
        assertEquals(fieldGetter.get(widget), "One");
        assertEquals(fieldGetter.getName(), "one");
        assertEquals(fieldGetter.getType(), String.class);
    }
}
