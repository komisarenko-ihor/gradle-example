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

//   dependency is used in runtime
//    runtimeOnly(libs.slf4j.simple) // <-- in case when we use libs.versions.toml
    runtimeOnly("org.slf4j:slf4j-simple") // <-- in case when we use gradle/platform
}