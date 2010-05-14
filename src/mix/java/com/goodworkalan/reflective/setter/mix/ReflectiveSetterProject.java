package com.goodworkalan.reflective.mix;

import com.goodworkalan.mix.ProjectModule;
import com.goodworkalan.mix.builder.Builder;
import com.goodworkalan.mix.builder.JavaProject;

public class ReflectiveSetterProject extends ProjectModule {
    @Override
    public void build(Builder builder) {
        builder
            .cookbook(JavaProject.class)
                .produces("com.github.bigeasy.reflective/reflective-setter/0.1")
                .main()
                    .depends()
                        .include("com.github.bigeasy.reflective/reflective/0.+1")
                        .end()
                    .end()
                .test()
                    .depends()
                        .include("org.testng/testng-jdk15/5.10")
                        .end()
                    .end()
                .end()
            .end();
    }
}
