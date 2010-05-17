package com.goodworkalan.reflective;

/**
 * Test a static initializer that raises an exception.
 *
 * @author Alan Gutierrez
 */
public class BadStatic {
    static {
        if (true) {
            throw new RuntimeException();
        }
    }
}
