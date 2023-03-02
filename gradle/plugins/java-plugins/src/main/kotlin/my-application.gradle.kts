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

tasks.build {
    dependsOn(tasks.named("bundle"))
}

tasks.register("buildAll") {
    description = "Builds even more!"

    dependsOn(tasks.build)
    dependsOn(tasks.named("countJars"))
}
