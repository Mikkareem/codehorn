package com.techullurgy.codehorn.domain.code.execution.usecases

import com.techullurgy.codehorn.domain.code.execution.services.ExecutionParametersProvider
import com.techullurgy.codehorn.domain.code.execution.services.FileService
import com.techullurgy.codehorn.domain.code.execution.services.InputFilePathProvider
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreateDockerFileUseCase(
    private val executionParametersProvider: ExecutionParametersProvider,
) {
    @Autowired
    private lateinit var inputFilePathProvider: ObjectProvider<InputFilePathProvider>

    operator fun invoke(submissionId: String) {
        val compiler = executionParametersProvider.getCompiler()
        val fileName = executionParametersProvider.getCodeFileName()
        val language = executionParametersProvider.getLanguage()

        val inputFilePath = inputFilePathProvider.getObject(submissionId).provide()

        val dockerFileContent = """
            |FROM $compiler
            |ADD ./$fileName /tmp/${language.lowercase()}/$fileName
            |COPY ./testcases /tmp/${language.lowercase()}/testcases
            |ENV testcase_no=0
            |WORKDIR /tmp/${language.lowercase()}/
            |CMD mkdir outputs
            ${executionParametersProvider.provide()}
        """.trimMargin()

        FileService.writeFile(filePath = "$inputFilePath/Dockerfile", value = dockerFileContent)
    }
}