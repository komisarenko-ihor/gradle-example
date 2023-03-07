package com.example.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.tasks.bundling.Zip;

public class MyJavaApplicationPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
//        plugins {
//            id("application")
//            id("my-java-base")
//        }
        project.getPlugins().apply("application");
        project.getPlugins().apply("my-java-base");

//        tasks.register<JarCount>("countJars") {
//            group = "My Group"
//            description = "Count of jars"
//
//            allJars.from(tasks.jar)
//            allJars.from(configurations.runtimeClasspath)
//
//            countFile.set(layout.buildDirectory.file("gen/count.txt"))
//        }
        project.getTasks().register("countJars", JarCount.class, t -> {
            t.setGroup("My Group");
            t.setDescription("Count of jars");

            t.getAllJars().from(project.getTasks().named("jar"));
            t.getAllJars().from(project.getConfigurations().getByName("runtimeClasspath"));

            t.getCountFile().set(project.getLayout().getBuildDirectory().file("gen/count.txt"));
        });

//        tasks.register<Zip>("bundle") {
//            group = "My Group"
//            description = "packages it all!"
//
//            from(tasks.jar)
//            from(configurations.runtimeClasspath)
//
//            destinationDirectory.set(layout.buildDirectory.dir("distribution"))
//        }
        project.getTasks().register("bundle", Zip.class, t -> {
            t.setGroup("My Group");
            t.setDescription("packages it all!");

            t.from(project.getTasks().named("jar"));
            t.from(project.getConfigurations().getByName("runtimeClasspath"));

            t.getDestinationDirectory().set(project.getLayout().getBuildDirectory().dir("distribution"));

        });

//        tasks.build {
//            group = "Some Group 1"
//            dependsOn(tasks.named("bundle"))
//        }
        project.getTasks().named("build", t -> {
            t.setGroup("Some Group 1");
            t.dependsOn(project.getTasks().named("bundle"));
        });

//        tasks.register("buildAll") {
//            group = "Some Group 2"
//            description = "Builds even more!"
//
//            dependsOn(tasks.build)
//            dependsOn(tasks.named("countJars"))
//        }
        project.getTasks().register("buildAll", Task.class, t -> {
            t.setGroup("Some Group 2");
            t.setDescription("Builds even more!");
            t.dependsOn(project.getTasks().named("build"));
            t.dependsOn(project.getTasks().named("countJars"));
        });
    }
}
