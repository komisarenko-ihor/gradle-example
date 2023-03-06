plugins {
    id("my-java-library")
}

dependencies {
    // dependency to platform project (gradle/platform)
    implementation(platform("com.example:platform"))
}

