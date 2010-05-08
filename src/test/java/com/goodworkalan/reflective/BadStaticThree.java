package com.goodworkalan.reflective;

public class BadStaticThree {
    static {
        if (true) {
            throw new RuntimeException();
        }
    }
}
