package com.goodworkalan.reflective;

import com.goodworkalan.cafe.ProjectModule;
import com.goodworkalan.cafe.builder.Builder;
import com.goodworkalan.cafe.outline.JavaProject;

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
                .produces("com.github.bigeasy.reflective/reflective-getter/0.5.0.1")
                .depends()
                    .development("org.testng/testng-jdk15/5.10")
                    .end()
                .end()
            .end();
    }
}
