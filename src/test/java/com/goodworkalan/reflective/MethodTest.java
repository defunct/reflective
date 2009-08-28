package com.goodworkalan.reflective;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.lang.reflect.Modifier;
import java.security.Permission;

import org.testng.annotations.Test;

public class MethodTest {
    @Test
    public void getNative() throws Exception {
        assertEquals(new ReflectiveFactory().getMethod(Basic.class, "foo").getNative(), Basic.class.getMethod("foo"));
    }

    @Test
    public void security() {
        SecurityManager sm = System.getSecurityManager();
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkMemberAccess(Class<?> clazz, int which) {
                if (clazz.equals(Security.class)) {
                    throw new SecurityException("No.");
                }
            }
            
            @Override
            public void checkPermission(Permission perm) {
            }
        });
        try {
            new ReflectiveFactory().getMethod(Security.class, "foo");
        } catch (ReflectiveException e) {
            System.setSecurityManager(sm);
            assertEquals(e.getCode(), ReflectiveException.SECURITY);
            return;
        }
        System.setSecurityManager(sm);
        fail("Expected exception not thrown.");
    }
    
    
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
