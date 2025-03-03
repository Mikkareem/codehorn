import org.gradle.kotlin.dsl.withType
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java-library")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    api(project(":common"))

    api("org.springframework.boot:spring-boot-starter-web")
    api("com.fasterxml.jackson.module:jackson-module-kotlin")
    api("org.jetbrains.kotlin:kotlin-reflect")

    api(platform("org.springframework.cloud:spring-cloud-dependencies:2024.0.0"))
    api("org.springframework.cloud:spring-cloud-starter-consul-discovery")
}


tasks.withType<BootJar>().configureEach {
    enabled = false
}