package com.goodworkalan.reflective.mix;

import com.goodworkalan.go.go.Artifact;
import com.goodworkalan.mix.ProjectModule;
import com.goodworkalan.mix.builder.Builder;
import com.goodworkalan.mix.builder.JavaProject;

public class ReflectiveProject extends ProjectModule {
    @Override
    public void build(Builder builder) {
        builder
            .cookbook(JavaProject.class)
                .produces(new Artifact("com.github.bigeasy.reflective/reflective/0.1"))
                .test()
                    .depends()
                        .artifact(new Artifact("org.testng/testng-jdk15/5.10"))
                        .end()
                    .end()
                .end()
            .end();
    }
}
