package com.goodworkalan.reflective;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.lang.reflect.Modifier;
import java.security.Permission;

import org.testng.annotations.Test;

/**
 * Unit tests for the {@link Method} class.
 *
 * @author Alan Gutierrez
 */
public class MethodTest {
    /** Test get native. */
    @Test
    public void getNative() throws Exception {
        assertEquals(new ReflectiveFactory().getMethod(Basic.class, "foo").getNative(), Basic.class.getMethod("foo"));
    }

    /** Test the security exception. */
    @Test
    public void security() {
        SecurityManager sm = System.getSecurityManager();
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkMemberAccess(Class<?> clazz, int which) {
                if (clazz.equals(Basic.class)) {
                    throw new SecurityException("No.");
                }
            }
            
            @Override
            public void checkPermission(Permission perm) {
            }
        });
        try {
            new ReflectiveFactory().getMethod(Basic.class, "foo");
        } catch (ReflectiveException e) {
            System.setSecurityManager(sm);
            assertEquals(e.getCode(), ReflectiveException.SECURITY);
            return;
        }
        System.setSecurityManager(sm);
        fail("Expected exception not thrown.");
    }
    
    /** Test the no such exception. */
    @Test
    public void noSuchMethod() {
        try {
            new ReflectiveFactory().getMethod(Basic.class, "baz");
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.NO_SUCH_METHOD);
            return;
        }
        fail("Expected exception not thrown.");
    }
    
    /** Test the illegal argument exception. */
    @Test
    public void illegalArgument() {
        try {
            new ReflectiveFactory().getMethod(Basic.class, "foo").invoke(new Basic(), "1");
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.ILLEGAL_ARGUMENT);
            return;
        }
        fail("Expected exception not thrown.");
    }
    
    /** Test the illegal access exception. */
    @Test
    public void illegalAccess() {
        for (java.lang.reflect.Method method : Basic.class.getDeclaredMethods()) {
            if (Modifier.isPrivate(method.getModifiers())) {
                try {
                    new Method(method).invoke(new Basic());
                } catch (ReflectiveException e) {
                    assertEquals(e.getCode(), ReflectiveException.ILLEGAL_ACCESS);
                    return;
                }
            }
        }
        fail("Expected exception not thrown.");
    }

    /** Test the invocation exception. */
    @Test
    public void invocationTarget() {
        try {
            new ReflectiveFactory().getMethod(Basic.class, "blam").invoke(new Basic());
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.INVOCATION_TARGET);
            return;
        }
        fail("Expected exception not thrown.");
    }

    /** Test static initialization exception. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void staticInitialization() throws ReflectiveException {
        try {
            new ReflectiveFactory().getMethod(BadStaticTwo.class, "test").invoke(null);
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.STATIC_INITIALIZER);
            throw e;
        }
    }
    
    /** Test equality. */
    @Test
    public void equality() throws ReflectiveException {
        Method blam = new ReflectiveFactory().getMethod(Basic.class, "blam");
        Method foo = new ReflectiveFactory().getMethod(Basic.class, "foo");
        assertEquals(foo.hashCode(), foo.hashCode());
        assertTrue(foo.equals(foo));
        assertFalse(foo.equals("foo"));
        assertFalse(foo.equals(blam));
    }
}
