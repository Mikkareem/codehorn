package com.techullurgy.codehorn.domain.code.execution.usecases

import com.techullurgy.codehorn.domain.code.execution.services.Compiler
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class DeleteDockerImageUseCase {
    operator fun invoke(submissionId: String) {
        val imageName = "${Compiler.BASE_IMAGE_PREFIX}-$submissionId"
        val builder = ProcessBuilder("docker", "rmi", "$imageName:latest")
        val process = builder.start();
        val isNotAborted = process.waitFor(10, TimeUnit.SECONDS)

        if (isNotAborted) {
            if (process.exitValue() == 0) {
                println("$imageName successfully deleted from docker registry")
            } else {
                println("$imageName not able to delete from docker registry")
            }
        }
    }
}