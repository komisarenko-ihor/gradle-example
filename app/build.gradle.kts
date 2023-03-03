plugins {
    id("my-application")
}

application {
    mainClass.set("com.example.MyApplication")
}

dependencies {
    implementation(project(":data-model"))
    implementation(project(":business-logic"))

    // dependency is used in runtime
    runtimeOnly("org.slf4j:slf4j-simple:1.7.36")
}