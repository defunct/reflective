package com.goodworkalan.reflective;

/**
 * Test a static initializer that raises an exception.
 *
 * @author Alan Gutierrez
 */
public class BadStaticOne {
    static {
        if (true) {
            throw new RuntimeException();
        }
    }
}
