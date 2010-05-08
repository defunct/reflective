package com.goodworkalan.reflective;

public class BadStaticTwo {
    static {
        if (true) {
            throw new RuntimeException();
        }
    }
    
    public void test() {
    }
}
