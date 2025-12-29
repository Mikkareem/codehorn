import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "2.3.0"
    kotlin("plugin.spring") version "2.3.0"
    id("org.springframework.boot") version "4.0.1"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "2.3.0"
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
        toolchain.languageVersion.set(JavaLanguageVersion.of(25))
    }

    // Configure Kotlin compiler options
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            freeCompilerArgs.set(listOf("-Xjsr305=strict", "-Xannotation-default-target=param-property"))
            jvmTarget.set(JvmTarget.JVM_25)
        }
    }

    if(name.contains("service")) {
        apply(plugin = "org.jetbrains.kotlin.plugin.spring")
        apply(plugin = "org.springframework.boot")
        apply(plugin = "io.spring.dependency-management")

        dependencies {
            implementation(project(":common-web"))

            testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
            testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        }

        tasks.test {
            useJUnitPlatform()
        }
    }
}

// Disable bootJar in the root project (since it's not a Spring Boot app)
tasks.withType<BootJar>().configureEach {
    enabled = false
}