package com.techullurgy.codehorn.java.execution.controllers

import com.techullurgy.codehorn.common.constants.EndpointConstants
import com.techullurgy.codehorn.common.model.CodeSubmissionResult
import com.techullurgy.codehorn.common.model.TestcaseResult
import com.techullurgy.codehorn.common.requests.CodeExecutionRequest
import com.techullurgy.codehorn.common.responses.CodeExecutionResultResponse
import com.techullurgy.codehorn.domain.code.execution.services.CodeExecutionService
import com.techullurgy.codehorn.domain.code.execution.services.CodeExecutionType
import com.techullurgy.codehorn.domain.code.execution.services.UserFileCreator
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(EndpointConstants.Public.LanguageExecution.POST_CODE_EXECUTION_JAVA)
class JavaExecutionController {

    @Autowired
    private lateinit var codeExecutionServiceProvider: ObjectProvider<CodeExecutionService>

    @PostMapping
    fun executeJavaCode(
        @RequestBody request: CodeExecutionRequest
    ): ResponseEntity<CodeExecutionResultResponse> {

        val codeExecutionService = codeExecutionServiceProvider.getObject(request.submissionId)

        val results = UserFileCreator(request.submissionId, "java").use {
            val outputs = codeExecutionService.executeFor(
                folder = it.file,
                fileContent = request.fileContent,
                executionType = CodeExecutionType.CONTINUE_IF_FAILS,
                testcases = request.sampleTestcases
            ).toMutableList()

            if(outputs.all { it.result == CodeSubmissionResult.Accepted } && request.hiddenTestcases.isNotEmpty()) {
                outputs += codeExecutionService.executeFor(
                    folder = it.file,
                    fileContent = request.fileContent,
                    executionType = CodeExecutionType.STOP_IF_FAILS,
                    testcases = request.hiddenTestcases
                )
            }

            outputs.toList()
        }

        val response = CodeExecutionResultResponse(
            submissionId = request.submissionId,
            testcaseResults = results
        )

        return ResponseEntity.ok(response)
    }
}