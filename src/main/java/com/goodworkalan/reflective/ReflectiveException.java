package com.goodworkalan.reflective;

public class ReflectiveException extends Exception {
    /** Serial version id. */
    private static final long serialVersionUID = 1L;
    
    public static final int CANNOT_FIND = 1;
    
    public static final int CANNOT_EXECUTE = 2;
    
    public static final int ILLEGAL_ARGUMENT = 201;
    
    public static final int INSTANCIATION = 202;
    
    public static final int ILLEGAL_ACCESS = 203;
    
    public static final int INVOCATION_TARGET = 204;
    
    public final static int SECURITY = 105;
    
    public final static int NO_SUCH_METHOD = 106;
    private final int code;
    
    public ReflectiveException(int code, Throwable cause) {
        super(cause.getMessage(), cause);
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
}
