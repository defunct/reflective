package com.goodworkalan.reflective;

import static com.goodworkalan.reflective.ReflectiveException.*;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.security.Permission;

import org.testng.annotations.Test;

/**
 * Unit tests for the {@link Reflective} class.
 *
 * @author Alan Gutierrez
 */
public class ReflectiveTest {
    /** Constructor for the sake of coverage. */
    @Test
    public void constructor() {
        new Reflective();
    }
    
    /** Test security exceptions. */
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
            exceptional(new Exceptional() {
                public void run() throws ReflectiveException {
                    Reflective.reflect(new Reflection<Constructor<Basic>>() {
                        public Constructor<Basic> reflect() throws NoSuchMethodException {
                            return Basic.class.getConstructor();
                        }
                    });
                }
            }, SECURITY);
        } finally {
            System.setSecurityManager(sm);
        }
    }
    
    /** Test no such method exception. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void noSuchMethod() throws ReflectiveException {
        exceptional(new Exceptional() {
            public void run() throws ReflectiveException {
                Reflective.reflect(new Reflection<Constructor<Basic>>() {
                    public Constructor<Basic> reflect() throws NoSuchMethodException {
                        return Basic.class.getConstructor(String.class);
                    }
                });
            }
        }, NO_SUCH_METHOD);
    }
    
    /** Test the no such field exception. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void noSuchField() throws ReflectiveException {
        exceptional(new Exceptional() {
            public void run() throws ReflectiveException {
                Reflective.reflect(new Reflection<Field>() {
                    public Field reflect() throws NoSuchFieldException {
                        return Basic.class.getField("snap");
                    }
                });
            }
        }, NO_SUCH_METHOD);
    }

    /** Test illegal argument exception. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void illegalArgument() throws ReflectiveException {
        exceptional(new Exceptional() {
            public void run() throws ReflectiveException {
                Reflective.reflect(new Reflection<Basic>() {
                    public Basic reflect()
                    throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
                        return Basic.class.getConstructor(int.class).newInstance("1");
                    }
                });
            }
        }, ILLEGAL_ARGUMENT);
    }

    /** Test instanciation exception. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void instanciation() throws ReflectiveException {
        exceptional(new Exceptional() {
            public void run() throws ReflectiveException {
                Reflective.reflect(new Reflection<Abstraction>() {
                    public Abstraction reflect()
                    throws InstantiationException, IllegalAccessException {
                        return Abstraction.class.newInstance();
                    }
                });
            }
        }, INSTANCIATION);
    }
    

    /** Test illegal argument exception. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void illegalAccess() throws ReflectiveException {
        exceptional(new Exceptional() {
            public void run() throws ReflectiveException {
                Reflective.reflect(new Reflection<Object>() {
                    public Object reflect()
                    throws InstantiationException, IllegalAccessException, InvocationTargetException {
                        for (java.lang.reflect.Method method : Basic.class.getDeclaredMethods()) {
                          if (Modifier.isPrivate(method.getModifiers())) {
                              return method.invoke(new Basic());
                            }
                        }
                        return null;
                    }
                });
            }
        }, ILLEGAL_ACCESS);
    }

    /** Test invocation target exception. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void invocationTarget() throws ReflectiveException {
        exceptional(new Exceptional() {
            public void run() throws ReflectiveException {
                Reflective.reflect(new Reflection<Basic>() {
                    public Basic reflect()
                    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
                        return Basic.class.getConstructor(double.class).newInstance(1.1);
                    }
                });
            }
        }, INVOCATION_TARGET);
    }
    
    /** Test static initialization exception. */
    @Test(expectedExceptions = ReflectiveException.class)
    public void staticInitialization() throws ReflectiveException {
        exceptional(new Exceptional() {
            public void run() throws ReflectiveException {
                Reflective.reflect(new Reflection<BadStatic>() {
                    public BadStatic reflect()
                    throws InstantiationException, IllegalAccessException {
                        return BadStatic.class.newInstance();
                    }
                });
            }
        }, STATIC_INITIALIZER);
    }

    /** Create a callback from a code block that throws a reflective exception. */
    private static interface Exceptional {
        public void run() throws ReflectiveException;
    }

    /**
     * Run the exceptional code block and check that the reflective exception
     * thrown has the given code.
     * 
     * @param runnable
     *            The exceptional code block.
     * @param code
     *            The expected error code.
     * @throws ReflectiveException
     *             If successful.
     */
    private static void exceptional(Exceptional runnable, int code) throws ReflectiveException {
        try { 
            runnable.run();
        } catch (ReflectiveException e) {
            assertEquals(e.getCode(), code);
            throw e;
        }
    }
}
