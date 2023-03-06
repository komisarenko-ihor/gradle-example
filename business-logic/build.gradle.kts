plugins {
    id("my-java-library")
}

dependencies {
    // dependency to platform project (gradle/platform)
    implementation(platform("com.example:platform"))

    implementation(project(":data-model"))
//    api(libs.commons.lang) <-- in case when we use libs.versions.toml
    api("org.apache.commons:commons-lang3")
//    implementation(libs.slf4j.api) <-- in case when we use libs.versions.toml
    implementation("org.slf4j:slf4j-api")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    // after creating sourceSets.create("integrationTest") we can add dependencies for this source
    integrationTestImplementation("org.junit.jupiter:junit-jupiter-api")
    integrationTestRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")


//    testRuntimeOnly("")
//    testCompileOnly("")

//    api("group:name") // <- Dependency is transitively visible at compilation

//    runtimeOnly("group:name") // <- Only at runtime
//    compileOnly("group:name") // <- Only at compile time
}

// Configuring dependencies with versions in project build file
//dependencies.constraints {
//    implementation("org.apache.commons:commons-lang3:3.12.0")
//    implementation("org.slf4j:slf4j-api:1.7.36")
//}

//configurations {
//    compileClasspath // <- Compile time "view" (aka Variant)
//    runtimeClasspath // <- Runtime "view" (aka Variant)
//}