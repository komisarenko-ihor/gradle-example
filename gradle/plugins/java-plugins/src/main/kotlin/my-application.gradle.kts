import com.example.gradle.JarCount
import com.example.gradle.SimpleTask

plugins {
    id("application")
    id("my-java-base")
}

tasks.register<SimpleTask>("simpleTask") {
    group = "My Group"
    description = "Task for printing message"

    setMessage("My message")
}

tasks.register<JarCount>("countJars") {
    group = "My Group"
    description = "Count of jars"

    allJars.from(tasks.jar)
    allJars.from(configurations.runtimeClasspath)

    countFile.set(layout.buildDirectory.file("gen/count.txt"))
}

tasks.register<Zip>("bundle") {
    group = "My Group"
    description = "packages it all!"

    from(tasks.jar)
    from(configurations.runtimeClasspath)

    destinationDirectory.set(layout.buildDirectory.dir("distribution"))
}

// adding task to lifecycle task
tasks.build {
    group = "Some Group 1"
    dependsOn(tasks.named("bundle"))
}

// creating a lifecycle task
tasks.register("buildAll") {
    group = "Some Group 2"
    description = "Builds even more!"

    dependsOn(tasks.build)
    dependsOn(tasks.named("countJars"))
}
