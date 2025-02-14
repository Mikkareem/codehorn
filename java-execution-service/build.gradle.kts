import org.gradle.kotlin.dsl.withType
import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation(project(":common"))
    implementation(project(":code-execution-domain"))
}


tasks.withType<BootJar> {
    mainClass.set("com.techullurgy.codehorn.java.execution.CodeHornJavaExecutionApplication")
}