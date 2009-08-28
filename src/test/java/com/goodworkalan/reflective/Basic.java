package com.goodworkalan.reflective;

public class Basic {
    public Basic(int integer) {
    }
    
    private Basic(char ch) {
    }
    
    public Basic(double d) {
        throw new UnsupportedOperationException();
    }
    
    public Basic() {
        this('.');
    }
    
    public void foo() {
        bar();
    }
    
    private void bar() {
    }
    
    public void blam() {
        throw new UnsupportedOperationException();
    }
}
