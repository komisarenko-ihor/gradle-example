package com.example.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.compile.JavaCompile;
import org.gradle.api.tasks.testing.Test;
import org.gradle.jvm.toolchain.JavaLanguageVersion;

public abstract class MyJavaBasePlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        // plugins {
        // id("java")
        project.getPlugins().apply("java");
        // id("com.diffplug.spotless")
        project.getPlugins().apply("com.diffplug.spotless");

        // sourceSets.create("integrationTests")
        String integrationTestSourceSet = "integrationTest";
        SourceSetContainer sourceSets = project.getExtensions().getByType(SourceSetContainer.class);
        sourceSets.create(integrationTestSourceSet);

//        tasks.register<Test>("integrationTest") {
//            testClassesDirs = sourceSets["integrationTest"].output.classesDirs
//            classpath = sourceSets["integrationTest"].runtimeClasspath
//
//            useJUnitPlatform ()
//        }
        project.getTasks().register(integrationTestSourceSet, Test.class, t -> {
            t.setTestClassesDirs(sourceSets.getByName("integrationTest")
                    .getOutput().getClassesDirs());
            t.setClasspath(sourceSets.getByName(integrationTestSourceSet)
                    .getRuntimeClasspath());
            t.useJUnitPlatform();
        });

//        dependencies.components {
//            withModule<Slf4jSimpleRule>("org.slf4j:slf4j-simple")
//        }
        project.getDependencies().getComponents()
                .withModule("org.slf4j:slf4j-simple", Slf4jSimpleRule.class);

//        java {
//            toolchain.languageVersion.set(JavaLanguageVersion.of(11))
//        }
        JavaPluginExtension java = project.getExtensions()
                .getByType(JavaPluginExtension.class);
        java.getToolchain().getLanguageVersion().set(JavaLanguageVersion.of(11));

//        tasks.withType<JavaCompile>().configureEach {
//            options.encoding = "UTF-8"
//        }
        project.getTasks().withType(JavaCompile.class).configureEach(t -> {
            t.getOptions().setEncoding("UTF-8");
        });

//        tasks.test {
//            useJUnitPlatform {
//                includeTags("slow") // <-- this setting is used with // to split test tasks #1
//            }
//
//            maxParallelForks = 4
//
//            maxHeapSize = "1g"
//        }
        project.getTasks().named("test", Test.class, t-> {
            t.useJUnitPlatform(junit -> junit.excludeTags("slow"));
            t.setMaxParallelForks(4);
            t.setMaxHeapSize("1g");
        });

//        tasks.register<Test>("testSlow") {
//            testClassesDirs = sourceSets.test.get().output.classesDirs
//            classpath = sourceSets.test.get().runtimeClasspath
//
//            useJUnitPlatform {
//                includeTags("slow")
//            }
//        }
        project.getTasks().register("testSlow", Test.class, t -> {
            t.setTestClassesDirs(sourceSets.getByName("test")
                    .getOutput().getClassesDirs());
            t.setClasspath(sourceSets.getByName("test")
                    .getRuntimeClasspath());
            t.useJUnitPlatform(junit -> junit.excludeTags("slow"));
        });

//        tasks.check {
//            dependsOn(tasks.named("testSlow"))
//        }
        project.getTasks().named("check", t -> {
            t.dependsOn(project.getTasks().named("testSlow"));
        });
    }
}
