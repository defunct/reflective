package com.goodworkalan.reflective.mix;

import com.goodworkalan.mix.ProjectModule;
import com.goodworkalan.mix.builder.Builder;
import com.goodworkalan.mix.builder.JavaProject;

/**
 * Builds the project definition for Reflective Setter.
 *
 * @author Alan Gutierrez
 */
public class ReflectiveSetterProject implements ProjectModule {
    /**
     * Build the project definition for Reflective Setter.
     *
     * @param builder
     *          The project builder.
     */
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
