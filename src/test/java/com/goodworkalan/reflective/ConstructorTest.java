package com.goodworkalan.reflective;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.lang.reflect.Modifier;
import java.security.Permission;

import org.testng.annotations.Test;

public class ConstructorTest {
    @Test
    public void getNative() throws Exception {
        assertEquals(new ReflectiveFactory().getConstructor(Basic.class).getNative(), Basic.class.getConstructor());
    }

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
            new ReflectiveFactory().getConstructor(Basic.class);
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
            new ReflectiveFactory().getConstructor(Basic.class, String.class);
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.NO_SUCH_METHOD);
            return;
        }
        fail("Expected exception not thrown.");
    }

    @Test
    public void illegalArgument() {
        try {
            new ReflectiveFactory().getConstructor(Basic.class, int.class).newInstance("1");
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.ILLEGAL_ARGUMENT);
            return;
        }
        fail("Expected exception not thrown.");
    }

    @Test
    public void instanciation() {
        try {
            new ReflectiveFactory().getConstructor(Abstraction.class).newInstance();
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.INSTANCIATION);
            return;
        }
        fail("Expected exception not thrown.");
    }
    

    @SuppressWarnings("unchecked")
    @Test
    public void illegalAccess() {
        for (java.lang.reflect.Constructor<?> constructor : Basic.class.getDeclaredConstructors()) {
            if (Modifier.isPrivate(constructor.getModifiers())) {
                try {
                    new Constructor<Basic>((java.lang.reflect.Constructor<Basic>) constructor).newInstance('c');
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
            new ReflectiveFactory().getConstructor(Basic.class, double.class).newInstance(1.1);
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.INVOCATION_TARGET);
            return;
        }
        fail("Expected exception not thrown.");
    }
    
    @Test
    public void equality() throws ReflectiveException {
        Constructor<Basic> one = new ReflectiveFactory().getConstructor(Basic.class, double.class);
        Constructor<Basic> two = new ReflectiveFactory().getConstructor(Basic.class, int.class);
        assertEquals(one.hashCode(), one.hashCode());
        assertTrue(one.equals(one));
        assertFalse(one.equals("one"));
        assertFalse(one.equals(two));
    }
}
