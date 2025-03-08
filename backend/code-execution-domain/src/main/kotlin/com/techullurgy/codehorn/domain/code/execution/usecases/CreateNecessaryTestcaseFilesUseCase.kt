package com.techullurgy.codehorn.domain.code.execution.usecases

import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.domain.code.execution.parsers.TestcaseParserStrategy
import com.techullurgy.codehorn.domain.code.execution.services.FileService
import com.techullurgy.codehorn.domain.code.execution.services.InputFilePathProvider
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File

@Component
class CreateNecessaryTestcaseFilesUseCase(
    private val testcaseParser: TestcaseParserStrategy
) {
    @Autowired
    private lateinit var inputFilePathProvider: ObjectProvider<InputFilePathProvider>

    operator fun invoke(submissionId: String, testcases: List<ProblemTestcase>) {
        val inputFilePath = inputFilePathProvider.getObject(submissionId).provide()

        testcases.filter { !it.isHidden }.forEach {
            val testcaseFilePath = "$inputFilePath/testcases/sample-input${it.id}.txt"
            FileService.writeFile(testcaseFilePath, testcaseParser.parse(it))
        }

        testcases.filter { it.isHidden }.forEach {
            val testcaseFilePath = "$inputFilePath/testcases/hidden-input${it.id}.txt"
            FileService.writeFile(testcaseFilePath, testcaseParser.parse(it))
        }
    }
}