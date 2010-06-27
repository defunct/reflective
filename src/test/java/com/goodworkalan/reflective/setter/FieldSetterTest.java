package com.goodworkalan.reflective.setter;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * Unit tests for the {@link FieldSetter} class.
 *
 * @author Alan Gutierrez
 */
public class FieldSetterTest {
    /** Test the field setter. */
    @Test
    public void fieldSetter() {
        Setter setter = Setters.getGetters(Person.class).get("firstName");
        assertEquals(setter.getType(), String.class);
        assertEquals(setter.getGenericType(), String.class);
    }
}
