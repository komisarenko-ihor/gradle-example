plugins {
    id("java")
    id("com.diffplug.spotless")
//    id("com.autonomousapps.dependency-analysis") <-- plugin for analysing dependencies
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

}

tasks.javadoc {

}