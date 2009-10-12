package com.goodworkalan.reflective.mix;

import com.goodworkalan.go.go.Artifact;
import com.goodworkalan.mix.BasicJavaModule;

public class ReflectiveModule extends BasicJavaModule {
    public ReflectiveModule() {
        super(new Artifact("com.goodworkalan", "reflective", "0.1"));
        addTestDependency(new Artifact("org.testng", "testng", "5.10"));
    }
}
