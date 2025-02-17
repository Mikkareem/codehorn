import org.gradle.kotlin.dsl.withType
import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation(project(":common"))
}

tasks.withType<BootJar> {
    mainClass.set("com.techullurgy.codehorn.codeexecution.CodeHornCodeExecutionApplication")
}