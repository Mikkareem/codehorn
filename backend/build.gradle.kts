import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.spring") version "2.1.0"
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "2.1.0"
}

group = "com.techullurgy.codehorn"

allprojects {
    version = "1.0.0"
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    // Configure Java toolchain
    extensions.configure<JavaPluginExtension> {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }

    // Configure Kotlin compiler options
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            freeCompilerArgs.set(listOf("-Xjsr305=strict"))
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    if(name.contains("service")) {
        apply(plugin = "org.jetbrains.kotlin.plugin.spring")
        apply(plugin = "org.springframework.boot")
        apply(plugin = "io.spring.dependency-management")

        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
            implementation("org.jetbrains.kotlin:kotlin-reflect")

            implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2024.0.0"))
            implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")

            testImplementation("org.springframework.boot:spring-boot-starter-test")
            testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
            testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        }
    }
}

// Disable bootJar in the root project (since it's not a Spring Boot app)
tasks.withType<BootJar>().configureEach {
    enabled = false
}