plugins {
    id("my-java-library")
}

dependencies {
    implementation(project(":data-model"))
    api("org.apache.commons:commons-lang3:3.12.0")
    implementation("org.slf4j:slf4j-api:1.7.36")

//    api("group:name") // <- Dependency is transitively visible at compilation

//    runtimeOnly("group:name") // <- Only at runtime
//    compileOnly("group:name") // <- Only at compile time
}

//configurations {
//    compileClasspath // <- Compile time "view" (aka Variant)
//    runtimeClasspath // <- Runtime "view" (aka Variant)
//}