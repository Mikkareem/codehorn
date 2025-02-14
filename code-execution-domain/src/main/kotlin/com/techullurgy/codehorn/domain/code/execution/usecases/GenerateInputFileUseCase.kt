package com.techullurgy.codehorn.domain.code.execution.usecases

import com.techullurgy.codehorn.domain.code.execution.services.ExecutionParametersProvider
import com.techullurgy.codehorn.domain.code.execution.services.FileService
import com.techullurgy.codehorn.domain.code.execution.services.InputFilePathProvider
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GenerateInputFileUseCase(
    private val parametersProvider: ExecutionParametersProvider
) {
    @Autowired
    private lateinit var inputFilePathProvider: ObjectProvider<InputFilePathProvider>

    operator fun invoke(
        submissionId: String,
        fileContent: String
    ) {
        val inputFilePath = inputFilePathProvider.getObject(submissionId).provide()
        FileService.writeFile("$inputFilePath/${parametersProvider.getCodeFileName()}", fileContent)
    }
}