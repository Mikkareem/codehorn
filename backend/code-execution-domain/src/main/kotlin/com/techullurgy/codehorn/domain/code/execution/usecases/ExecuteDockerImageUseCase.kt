package com.techullurgy.codehorn.domain.code.execution.usecases

import com.techullurgy.codehorn.domain.code.execution.services.ExecutionParametersProvider
import com.techullurgy.codehorn.domain.code.execution.services.InputFilePathProvider
import com.techullurgy.codehorn.domain.code.execution.services.utils.getErrors
import com.techullurgy.codehorn.domain.code.execution.services.utils.getOutputs
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class ExecuteDockerImageUseCase(
    private val executionParametersProvider: ExecutionParametersProvider
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var inputFilePathProvider: ObjectProvider<InputFilePathProvider>

    operator fun invoke(submissionId: String, imageName: String): List<String> {

        val inputFilePath = inputFilePathProvider.getObject(submissionId).provide()

        val builder = ProcessBuilder(
            "docker",
            "run",
            "--rm",
            "-v",
            "./$inputFilePath/outputs:/tmp/${executionParametersProvider.getLanguage()}/outputs",
            "--name",
            "$imageName-container",
            imageName
        )
        val process = builder.start()
        val isNotAborted = process.waitFor(2, TimeUnit.MINUTES)

        if (isNotAborted) {
            val outputs = process.getOutputs()
            val error = process.getErrors()
            if(error.isNotBlank()) {
                logger.error(error)
            }
            if(outputs.isNotEmpty()) {
                logger.debug(outputs.toString())
            }
            return outputs
        } else {
            logger.error("Aborting the container")
            forceStopAndDeleteContainer(imageName)
            process.destroy()
            process.waitFor()
        }

        return emptyList()
    }

    private fun forceStopAndDeleteContainer(imageName: String) {
        val builder = ProcessBuilder("docker", "stop", "$imageName-container")
        val process = builder.start()
        process.waitFor()
    }
}