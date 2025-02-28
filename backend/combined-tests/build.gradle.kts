import org.gradle.kotlin.dsl.withType
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    testImplementation(project(":common"))
    // Spring Boot Starter Test (JUnit, MockMvc, AssertJ, Mockito, etc.)
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Kotlin Testing Libraries
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // WebClient / RestClient (for testing external APIs)
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true  // Print logs from System.out & System.err
        events("PASSED", "FAILED", "SKIPPED") // Show test execution events
    }
}

// Disable bootJar in the root project (since it's not a Spring Boot app)
tasks.withType<BootJar>().configureEach {
    enabled = false
}