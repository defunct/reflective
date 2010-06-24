package com.goodworkalan.reflective;

// TODO Document.
public class Basic {
    // TODO Document.
    @SuppressWarnings("unused")
    private String secret;

    // TODO Document.
    public String field;

    // TODO Document.
    public Basic(int integer) {
    }
    
    // TODO Document.
    private Basic(char ch) {
    }
    
    // TODO Document.
    public Basic(double d) {
        throw new UnsupportedOperationException();
    }
    
    // TODO Document.
    public Basic() {
        this('.');
    }
    
    // TODO Document.
    public void foo() {
        bar();
    }
    
    // TODO Document.
    private void bar() {
    }
    
    // TODO Document.
    public void blam() {
        throw new UnsupportedOperationException();
    }
}
