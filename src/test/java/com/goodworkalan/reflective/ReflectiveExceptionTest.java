package com.goodworkalan.reflective;
import static com.goodworkalan.reflective.Reflective.ILLEGAL_ARGUMENT;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class ReflectiveExceptionTest {
    @Test
    public void constructor() {
        try {
            try {
                throw new IllegalAccessException();
            } catch (IllegalAccessException e) {
                throw new ReflectiveException(ILLEGAL_ARGUMENT, e);
            }
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ILLEGAL_ARGUMENT);
        }
    }
}
