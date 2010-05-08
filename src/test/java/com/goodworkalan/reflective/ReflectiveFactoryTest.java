package com.goodworkalan.reflective;

import static org.testng.Assert.assertEquals;

import java.security.Permission;

import org.testng.annotations.Test;

/**
 * Unit tests for the {@link ReflectiveFactory} class.
 *
 * @author Alan Gutierrez
 */
public class ReflectiveFactoryTest {
    /** Test new instance security exceptions. */
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
        try {
            new ReflectiveFactory().newInstance(Basic.class);
        } catch (ReflectiveException e) {
            System.setSecurityManager(sm);
            assertEquals(e.getCode(), ReflectiveException.SECURITY);
            throw e;
        } finally {
            System.setSecurityManager(sm);
        }
    }
    
    /** Test new instance instanciation exception. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void illegalAccess() throws ReflectiveException {
        try {
            new ReflectiveFactory().newInstance(PrivateConstructor.class);
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.ILLEGAL_ACCESS);
            throw e;
        }
    }

    /** Test new instance instanciation exception. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void instanciation() throws ReflectiveException {
        try {
            new ReflectiveFactory().newInstance(Abstraction.class);
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.INSTANCIATION);
            throw e;
        }
    }
    
    /** Test new instance static initialization exception. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void staticInitialization() throws ReflectiveException {
        try {
            new ReflectiveFactory().newInstance(BadStaticThree.class);
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), ReflectiveException.STATIC_INITIALIZER);
            throw e;
        }
    }
}
