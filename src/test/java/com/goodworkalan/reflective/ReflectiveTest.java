package com.goodworkalan.reflective;

import static com.goodworkalan.reflective.Reflective.ILLEGAL_ACCESS;
import static com.goodworkalan.reflective.Reflective.ILLEGAL_ARGUMENT;
import static com.goodworkalan.reflective.Reflective.INSTANCIATION;
import static com.goodworkalan.reflective.Reflective.INVOCATION_TARGET;
import static com.goodworkalan.reflective.Reflective.NO_SUCH_METHOD;
import static com.goodworkalan.reflective.Reflective.SECURITY;
import static com.goodworkalan.reflective.Reflective.STATIC_INITIALIZER;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
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
    @Test(expectedExceptions = ReflectionException.class)
    public void security() throws ReflectionException {
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
                public void run() throws ReflectionException {
                    reflect(new Reflection<Constructor<Basic>>() {
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
    @Test(expectedExceptions = ReflectionException.class)
    public void noSuchMethod() throws ReflectionException {
        exceptional(new Exceptional() {
            public void run() throws ReflectionException {
                reflect(new Reflection<Constructor<Basic>>() {
                    public Constructor<Basic> reflect() throws NoSuchMethodException {
                        return Basic.class.getConstructor(String.class);
                    }
                });
            }
        }, NO_SUCH_METHOD);
    }
    
    /** Test the no such field exception. */
    @Test(expectedExceptions = ReflectionException.class)
    public void noSuchField() throws ReflectionException {
        exceptional(new Exceptional() {
            public void run() throws ReflectionException {
                reflect(new Reflection<Field>() {
                    public Field reflect() throws NoSuchFieldException {
                        return Basic.class.getField("snap");
                    }
                });
            }
        }, NO_SUCH_METHOD);
    }

    /** Test illegal argument exception. */
    @Test(expectedExceptions = ReflectionException.class)
    public void illegalArgument() throws ReflectionException {
        exceptional(new Exceptional() {
            public void run() throws ReflectionException {
                reflect(new Reflection<Basic>() {
                    public Basic reflect()
                    throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
                        return Basic.class.getConstructor(int.class).newInstance("1");
                    }
                });
            }
        }, ILLEGAL_ARGUMENT);
    }

    /** Test instanciation exception. */
    @Test(expectedExceptions = ReflectionException.class)
    public void instanciation() throws ReflectionException {
        exceptional(new Exceptional() {
            public void run() throws ReflectionException {
                reflect(new Reflection<Abstraction>() {
                    public Abstraction reflect()
                    throws InstantiationException, IllegalAccessException {
                        return Abstraction.class.newInstance();
                    }
                });
            }
        }, INSTANCIATION);
    }
    

    /** Test illegal argument exception. */
    @Test(expectedExceptions = ReflectionException.class)
    public void illegalAccess() throws ReflectionException {
        exceptional(new Exceptional() {
            public void run() throws ReflectionException {
                reflect(new Reflection<Object>() {
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
    @Test(expectedExceptions = ReflectionException.class)
    public void invocationTarget() throws ReflectionException {
        exceptional(new Exceptional() {
            public void run() throws ReflectionException {
                reflect(new Reflection<Basic>() {
                    public Basic reflect()
                    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
                        return Basic.class.getConstructor(double.class).newInstance(1.1);
                    }
                });
            }
        }, INVOCATION_TARGET);
    }
    
    /** Test static initialization exception. */
    @Test(expectedExceptions = ReflectionException.class)
    public void staticInitialization() throws ReflectionException {
        exceptional(new Exceptional() {
            public void run() throws ReflectionException {
                reflect(new Reflection<BadStatic>() {
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
        public void run() throws ReflectionException;
    }
    
    /** Test passing encode an error. */
    @Test(expectedExceptions = Error.class)
    public void error() {
        Reflective.encode(new Error());
    }
    
    /** Test passing encode an unknown runtime exception. */
    @Test(expectedExceptions = RuntimeException.class)
    public void runtimeException() {
        Reflective.encode(new RuntimeException());
    }
    
    /** Test passing encode an unknown exception. */
    @Test(expectedExceptions = RuntimeException.class)
    public void exception() {
        Reflective.encode(new IOException());
    }

    /**
     * Run the exceptional code block and check that the reflective exception
     * thrown has the given code.
     * 
     * @param runnable
     *            The exceptional code block.
     * @param code
     *            The expected error code.
     * @throws ReflectionException
     *             If successful.
     */
    private static void exceptional(Exceptional runnable, int code) throws ReflectionException {
        try { 
            runnable.run();
        } catch (ReflectionException e) {
            assertEquals(e.getCode(), code);
            throw e;
        }
    }
    
    static <T> void reflect(Reflection<T> reflection) throws ReflectionException {
        try {
            reflection.reflect();
        } catch (Throwable e) {
            throw new ReflectionException(Reflective.encode(e), e);
        }
    }
}
