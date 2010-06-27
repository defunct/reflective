package com.goodworkalan.reflective.setter;

/**
 * An example Java bean.
 * 
 * @author Alan Gutierrez
 */
public class Person {
    /** The first name. */
    public String firstName;

    /** The last name. */
    private String lastName;

    /**
     * Get the last name.
     * 
     * @param lastName
     *            The last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Set the last name.
     * 
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }
}
