package com.goodworkalan.reflective;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.*;

import java.io.File;
import java.lang.reflect.Modifier;
import java.security.Permission;

import org.testng.annotations.Test;

/**
 * Unit tests for {@link Field}.
 *
 * @author Alan Gutierrez
 */
public class FieldTest {
    /** Test the field constructor. */
    @Test
    public void constructor() throws SecurityException, NoSuchFieldException, ReflectiveException {
        assertEquals(new ReflectiveFactory().getField(Basic.class, "field").getNative(), Basic.class.getField("field"));
    }
    
    /** Test the no such field exception. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void noSuchField() throws ReflectiveException {
        ReflectiveFactory factory = new ReflectiveFactory();
        try {
            factory.getField(Basic.class, "snap").getNative();
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.NO_SUCH_METHOD);
            throw e;
        }
    }
    
    /** Test the no such field exception. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void security() throws ReflectiveException {
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
        ReflectiveFactory factory = new ReflectiveFactory();
        try {
            factory.getField(Basic.class, "field").getNative();
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.SECURITY);
            throw e;
        } finally {
            System.setSecurityManager(sm);
        }
    }
    
    /** Test field get. */
    @Test
    public void get() throws ReflectiveException {
        Basic basic = new Basic();
        basic.field = "A";
        Field field = new ReflectiveFactory().getField(Basic.class, "field");
        assertEquals(field.get(basic), "A");
    }

    /** Test field set. */
    @Test
    public void set() throws ReflectiveException {
        Basic basic = new Basic();
        Field field = new ReflectiveFactory().getField(Basic.class, "field");
        field.set(basic, "A");
        assertEquals(basic.field, "A");
    }
    
    /** Test field set wrong object type. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void setIllegalArgument() throws ReflectiveException {
        try {
            new ReflectiveFactory().getField(Basic.class, "field").set(new File("."), "A");
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.ILLEGAL_ARGUMENT);
            throw e;
        }
    }
    
    /** Test field get wrong object type. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void getIllegalArgument() throws ReflectiveException {
        try {
            new ReflectiveFactory().getField(Basic.class, "field").get(new File("."));
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.ILLEGAL_ARGUMENT);
            throw e;
        }
    }
    
    /** Test field get illegal access. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void getIllegalAccess() throws ReflectiveException {
        for (java.lang.reflect.Field field : Basic.class.getDeclaredFields()) {
            if (Modifier.isPrivate(field.getModifiers())) {
                try {
                    new Field(field).get(new Basic());
                } catch (ReflectiveException e) {
                    assertEquals(e.getCode(), ReflectiveException.ILLEGAL_ACCESS);
                    throw e;
                }
            }
        }
    }
    
    /** Test field set illegal access. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void setIllegalAccess() throws ReflectiveException {
        for (java.lang.reflect.Field field : Basic.class.getDeclaredFields()) {
            if (Modifier.isPrivate(field.getModifiers())) {
                try {
                    new Field(field).set(new Basic(), "A");
                } catch (ReflectiveException e) {
                    assertEquals(e.getCode(), ReflectiveException.ILLEGAL_ACCESS);
                    throw e;
                }
            }
        }
    }

    /** Test the equals method. */
    @Test
    public void equality() throws ReflectiveException, SecurityException, NoSuchFieldException {
        Field field = new ReflectiveFactory().getField(Basic.class, "field");
        assertFalse(field.equals(null));
        assertTrue(field.equals(new Field(Basic.class.getField("field"))));
    }
    
    /** Test the hash code. */
    @Test
    public void hash() throws ReflectiveException {
        Field field = new ReflectiveFactory().getField(Basic.class, "field");
        assertEquals(field.hashCode(), field.getNative().hashCode());
    }
}
