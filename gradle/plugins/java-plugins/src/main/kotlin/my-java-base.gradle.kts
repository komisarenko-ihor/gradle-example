import com.example.gradle.Slf4jSimpleRule

plugins {
    id("java")
    id("com.diffplug.spotless")
//    id("com.autonomousapps.dependency-analysis") <-- plugin for analysing dependencies
}

// we can configure sources
sourceSets.main {
//    java.setSrcDirs(listOf(layout.projectDirectory.dir("sources")))
}
sourceSets.test {

}
sourceSets.create("integrationTest") // <-- after that we can add new SourceSet folders test/integrationTest and

/*
    Adjust dependencies
    to see dependency graph call ./gradlew :app:dependencies --configuration runtimeClasspath
 */
dependencies.components {
    withModule<Slf4jSimpleRule>("org.slf4j:slf4j-simple")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

//tasks.compileJava {
//    options.encoding = "UTF-8"
//}
//
//tasks.compileTestJava {
//    options.encoding = "UTF-8"
//}

tasks.test {
    useJUnitPlatform {
        includeTags("slow") // <-- this setting is used with // to split test tasks #1
    }

    maxParallelForks = 4

    maxHeapSize = "1g"
}

// to split test tasks #1
tasks.register<Test>("testSlow") {
    testClassesDirs = sourceSets.test.get().output.classesDirs
    classpath = sourceSets.test.get().runtimeClasspath

    useJUnitPlatform {
        includeTags("slow")
    }
}

tasks.check {
    dependsOn(tasks.named("testSlow"))
}

// to split test tasks #2
tasks.register<Test>("integrationTest") {
    testClassesDirs = sourceSets["integrationTest"].output.classesDirs
    classpath = sourceSets["integrationTest"].runtimeClasspath

    useJUnitPlatform ()
}