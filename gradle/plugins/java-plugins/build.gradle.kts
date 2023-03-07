plugins {
    `kotlin-dsl` // id("kotlin-dsl")
    /*
        in case when you write build configuration on Java:
            - com.example.gradle.MyJavaBasePlugin
            - com.example.gradle.MyJavaLibraryPlugin
            - com.example.gradle.MyJavaApplicationPlugin
     */
    id("java-gradle-plugin") //
}

// for java Build Configuration
gradlePlugin {
    plugins.create("MyJavaBase") {
        id = "my-java-base"
        implementationClass = "com.example.gradle.MyJavaBasePlugin"
    }
    plugins.create("MyJavaLibrary") {
        id = "my-java-library"
        implementationClass = "com.example.gradle.MyJavaLibraryPlugin"
    }
    plugins.create("MyJavaApplication") {
        id = "my-application"
        implementationClass = "com.example.gradle.MyJavaApplicationPlugin"
    }
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.8.0")
}