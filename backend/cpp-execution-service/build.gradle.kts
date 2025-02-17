import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.withType<BootJar> {
    mainClass.set("com.techullurgy.codehorn.cpp.execution.CodeHornCppExecutionApplication")
}