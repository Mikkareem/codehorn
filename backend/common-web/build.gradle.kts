import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    api(project(":common"))

    api("org.springframework.boot:spring-boot-starter-webmvc")
    api("org.springframework.boot:spring-boot-starter-restclient")
    api("org.jetbrains.kotlin:kotlin-reflect")
    api("tools.jackson.module:jackson-module-kotlin")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("org.springframework.boot:spring-boot-starter-restclient-test")

    api(platform("org.springframework.cloud:spring-cloud-dependencies:2025.1.0"))
}


tasks.withType<BootJar>().configureEach {
    enabled = false
}