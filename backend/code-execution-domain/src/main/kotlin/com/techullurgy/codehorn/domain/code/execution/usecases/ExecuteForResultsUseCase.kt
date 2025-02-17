package com.techullurgy.codehorn.domain.code.execution.usecases

import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.domain.code.execution.services.CodeExecutionResult
import com.techullurgy.codehorn.domain.code.execution.services.CodeExecutionType
import com.techullurgy.codehorn.domain.code.execution.services.Compiler
import org.springframework.stereotype.Component

@Component
class ExecuteForResultsUseCase(
    private val executeDockerImage: ExecuteDockerImageUseCase
) {
    operator fun invoke(
        submissionId: String,
        testcases: List<ProblemTestcase>,
        executionType: CodeExecutionType
    ): Array<CodeExecutionResult> {

        val imageName = "${Compiler.BASE_IMAGE_PREFIX}-$submissionId"

        val results = Array<CodeExecutionResult>(testcases.size) { CodeExecutionResult.NotExecuted }

        for (index in testcases.indices) {
            val result = executeDockerImage(submissionId, imageName, index + 1)

            if (result.first) {
                results[index] = CodeExecutionResult.Accepted
            } else {
                if (result.second == CodeExecutionResult.TimeLimitExceeded.name) {
                    results[index] = CodeExecutionResult.TimeLimitExceeded
                    break
                } else if (result.second == "") {
                    results[index] = CodeExecutionResult.WrongAnswer
                } else {
                    results[index] = CodeExecutionResult.RuntimeError(result.second)
                    break
                }
                if(executionType == CodeExecutionType.STOP_IF_FAILS) {
                    break
                }
            }
        }

        return results
    }
}