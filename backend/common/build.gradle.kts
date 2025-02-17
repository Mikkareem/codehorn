import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
}

// Disable bootJar in the root project (since it's not a Spring Boot app)
tasks.withType<BootJar>().configureEach {
    enabled = false
}