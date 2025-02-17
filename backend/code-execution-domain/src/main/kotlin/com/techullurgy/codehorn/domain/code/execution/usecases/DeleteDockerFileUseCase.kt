package com.techullurgy.codehorn.domain.code.execution.usecases

import com.techullurgy.codehorn.domain.code.execution.services.FileService
import com.techullurgy.codehorn.domain.code.execution.services.InputFilePathProvider
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DeleteDockerFileUseCase {

    @Autowired
    private lateinit var inputFilePathProvider: ObjectProvider<InputFilePathProvider>

    operator fun invoke(submissionId: String) {
        val inputFilePath = inputFilePathProvider.getObject(submissionId).provide()
        FileService.deleteFile("$inputFilePath/Dockerfile")
    }
}