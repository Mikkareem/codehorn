package com.techullurgy.codehorn.domain.code.execution.usecases

import com.techullurgy.codehorn.domain.code.execution.services.CodeExecutionResult
import com.techullurgy.codehorn.domain.code.execution.services.Compiler
import com.techullurgy.codehorn.domain.code.execution.services.ExecutionParametersProvider
import com.techullurgy.codehorn.domain.code.execution.services.InputFilePathProvider
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class ExecuteDockerImageUseCase(
    private val executionParametersProvider: ExecutionParametersProvider
) {
    @Autowired
    private lateinit var inputFilePathProvider: ObjectProvider<InputFilePathProvider>

    operator fun invoke(submissionId: String, imageName: String, testcaseNo: Int): Pair<Boolean, String> {

        val inputFilePath = inputFilePathProvider.getObject(submissionId).provide()

        val builder = ProcessBuilder(
            "docker",
            "run",
            "--rm",
            "-v",
            "$inputFilePath/outputs:/tmp/${executionParametersProvider.getLanguage()}/outputs",
            "--name",
            "$imageName-container",
            "-e",
            "testcase_no=$testcaseNo",
            imageName
        )
        val process = builder.start()
        val isNotAborted = process.waitFor(5, TimeUnit.SECONDS)

        if (isNotAborted) {
            return when(process.exitValue()) {
                Compiler.ACCEPTED_PROCESS_EXIT_CODE -> Pair(true, "")
                Compiler.WRONG_ANSWER_PROCESS_EXIT_CODE -> Pair(false, "")
                Compiler.TIME_LIMIT_EXCEEDED_PROCESS_EXIT_CODE -> Pair(false, CodeExecutionResult.TimeLimitExceeded.name)
                else -> {
//                    val error = process.getErrorData()
                    Pair(false, "")//error)
                }
            }
        } else {
            forceStopAndDeleteContainer(imageName)
            process.destroy()
            process.waitFor()
        }

        return Pair(false, CodeExecutionResult.TimeLimitExceeded.name)
    }

    private fun forceStopAndDeleteContainer(imageName: String) {
        val builder = ProcessBuilder("docker", "stop", "$imageName-container")
        val process = builder.start()
        process.waitFor()
    }
}