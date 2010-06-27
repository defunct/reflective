package com.goodworkalan.reflective.setter;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * Unit tests for the {@link MethodSetter} class.
 *
 * @author Alan Gutierrez
 */
public class MethodSetterTest {
    /** Test the method setter. */
    @Test
    public void methodSetter() {
        Setter setter = Setters.getGetters(Person.class).get("lastName");
        assertEquals(setter.getType(), String.class);
        assertEquals(setter.getGenericType(), String.class);
    }
}
