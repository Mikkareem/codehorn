package com.techullurgy.codehorn.domain.code.execution.services

import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.domain.code.execution.usecases.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class CodeExecutionService(
    private val submissionId: String
) {
    @Autowired private lateinit var buildDockerImageUseCase: BuildDockerImageUseCase
    @Autowired private lateinit var createDockerFileUseCase: CreateDockerFileUseCase
    @Autowired private lateinit var createNecessaryTestcaseFilesUseCase: CreateNecessaryTestcaseFilesUseCase
    @Autowired private lateinit var executeForResults: ExecuteForResultsUseCase
    @Autowired private lateinit var generateInputFileUseCase: GenerateInputFileUseCase
    @Autowired private lateinit var deleteDockerImageUseCase: DeleteDockerImageUseCase
    @Autowired private lateinit var deleteDockerFileUseCase: DeleteDockerFileUseCase

    fun executeFor(
        fileContent: String,
        testcases: List<ProblemTestcase>,
        executionType: CodeExecutionType
    ): List<CodeExecutionResult> {
        generateInputFileUseCase(submissionId, fileContent)

        createNecessaryTestcaseFilesUseCase(submissionId, testcases)

        createDockerFileUseCase(submissionId)

        val isCreated = buildDockerImageUseCase(submissionId)

        val results = if(isCreated) {
            val results = executeForResults(submissionId, testcases, executionType)
            results.toList().filter { it !is CodeExecutionResult.NotExecuted }
        } else {
            listOf(CodeExecutionResult.CompilationError(""))
        }

        deleteDockerImageUseCase(submissionId)

        deleteDockerFileUseCase(submissionId)

        return results
    }
}