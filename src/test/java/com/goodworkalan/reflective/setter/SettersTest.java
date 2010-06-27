package com.goodworkalan.reflective.setter;

import java.util.Map;
import static org.testng.Assert.*;

import org.testng.annotations.Test;

/**
 * Unit tests for the {@link Setters} class. 
 *
 * @author Alan Gutierrez
 */
public class SettersTest {
    /** Test setters. */
    @Test
    public void setters() {
        Map<String, Setter> setters = Setters.getGetters(Person.class);
        assertTrue(setters.containsKey("firstName"));
        assertTrue(setters.containsKey("lastName"));
    }
}
