package com.goodworkalan.reflective.mix;

import com.goodworkalan.mix.ProjectModule;
import com.goodworkalan.mix.builder.Builder;
import com.goodworkalan.mix.builder.JavaProject;

/**
 * Builds the project definition for Reflective Getter.
 *
 * @author Alan Gutierrez
 */
public class ReflectiveGetterProject implements ProjectModule {
    /**
     * Build the project definition for Reflective Setter.
     *
     * @param builder
     *          The project builder.
     */
    public void build(Builder builder) {
        builder
            .cookbook(JavaProject.class)
                .produces("com.github.bigeasy.reflective/reflective-getter/0.5")
                .depends()
                    .production("com.github.bigeasy.reflective/reflective-exception/0.+5")
                    .development("org.testng/testng-jdk15/5.10")
                    .end()
                .end()
            .end();
    }
}
