package com.goodworkalan.reflective.getter;

/**
 * A bean to test getter reflection.
 *
 * @author Alan Gutierrez
 */
public class Widget {
    /** A public field. */
    public String one;
    
    /** A private field with accessors. */
    private String two;

    /**
     * Set the private field.
     * 
     * @param two
     *            The value.
     */
    public void setTwo(String two) {
        this.two = two;
    }

    /**
     * Get the private field.
     * 
     * @return The private field value.
     */
    public String getTwo() {
        return two;
    }
}
