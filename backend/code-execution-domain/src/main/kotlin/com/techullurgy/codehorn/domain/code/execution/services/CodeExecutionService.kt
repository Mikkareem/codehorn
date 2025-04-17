package com.techullurgy.codehorn.domain.code.execution.services

import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.common.model.TestcaseResult
import com.techullurgy.codehorn.domain.code.execution.usecases.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.io.File

@Component
@Scope("prototype")
class CodeExecutionService(
    private val submissionId: String
) {
    @Autowired private lateinit var createEntryPointFileUseCase: CreateEntryPointFileUseCase
    @Autowired private lateinit var createDockerFileUseCase: CreateDockerFileUseCase
    @Autowired private lateinit var buildDockerImageUseCase: BuildDockerImageUseCase
    @Autowired private lateinit var createNecessaryTestcaseFilesUseCase: CreateNecessaryTestcaseFilesUseCase
    @Autowired private lateinit var executeForResults: ExecuteForResultsUseCase
    @Autowired private lateinit var generateInputFileUseCase: GenerateInputFileUseCase
    @Autowired private lateinit var deleteDockerImageUseCase: DeleteDockerImageUseCase
    @Autowired private lateinit var generateTestcaseResultsUseCase: GenerateTestcaseResultsUseCase

    fun executeFor(
        folder: File,
        fileContent: String,
        testcases: List<ProblemTestcase>,
    ): List<TestcaseResult> {
        generateInputFileUseCase(submissionId, fileContent)

        createNecessaryTestcaseFilesUseCase(submissionId, testcases)

        createEntryPointFileUseCase(submissionId, testcases)

        createDockerFileUseCase(submissionId)

        val isCreated = buildDockerImageUseCase(submissionId)

        val results = executeForResults(submissionId, testcases, isCreated)

        if(isCreated) {
            deleteDockerImageUseCase(submissionId)
        }

        return generateTestcaseResultsUseCase(folder, testcases, results)
    }
}