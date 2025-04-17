package com.techullurgy.codehorn.domain.code.execution.usecases

import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.domain.code.execution.services.EntryPointProvider
import com.techullurgy.codehorn.domain.code.execution.services.FileService
import com.techullurgy.codehorn.domain.code.execution.services.InputFilePathProvider
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CreateEntryPointFileUseCase {

    @Autowired
    private lateinit var entryPointProvider: ObjectProvider<EntryPointProvider>

    @Autowired
    private lateinit var inputFilePathProvider: ObjectProvider<InputFilePathProvider>

    operator fun invoke(submissionId: String, testcases: List<ProblemTestcase>) {
        val inputFilePath = inputFilePathProvider.getObject(submissionId).provide()

        val entryPointContent = entryPointProvider.getObject(testcases).provide()

        FileService.writeFile(filePath = "$inputFilePath/entrypoint.sh", value = entryPointContent)
    }
}