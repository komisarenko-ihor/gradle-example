plugins {
    id("my-application")
}

application {
    mainClass.set("com.example.MyApplication")
}

dependencies {
    // dependency to platform project (gradle/platform)
    implementation(platform("com.example:platform"))

    implementation(project(":data-model"))
    implementation(project(":business-logic"))

    // dependency is used in runtime
    runtimeOnly("org.slf4j:slf4j-simple")
}