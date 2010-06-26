package com.goodworkalan.reflective;

/**
 * A class to test reflection that causes all kinds of problems.
 *
 * @author Alan Gutierrez
 */
public class Basic {
    /** Hidden member, cannot be called. */
    @SuppressWarnings("unused")
    private String secret;

    /** A public field. */
    public String field;

    /**
     * A public constructor.
     * 
     * @param integer
     *            The magic number.
     */
    public Basic(int integer) {
    }
    
    /**
     * A private constructor.
     * 
     * @param ch
     *            The scarlet letter.
     */
    private Basic(char ch) {
    }

    /**
     * A public constructor that raises an exception.
     * 
     * @param d
     *            On the double.
     */
    public Basic(double d) {
        throw new UnsupportedOperationException();
    }
    
    /**
     * The default constructor.
     */
    public Basic() {
        this('.');
    }
    
    /** A public method. */
    public void foo() {
        bar();
    }
    
    /** A private method. */
    private void bar() {
    }
    
    /** A method that always throws an exception. */
    public void blam() {
        throw new UnsupportedOperationException();
    }
}
