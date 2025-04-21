package com.techullurgy.codehorn.javascript.execution.controllers

import com.techullurgy.codehorn.common.constants.EndpointConstants
import com.techullurgy.codehorn.common.web.requests.CodeExecutionRequest
import com.techullurgy.codehorn.common.web.responses.CodeExecutionResultResponse
import com.techullurgy.codehorn.domain.code.execution.services.CodeExecutionService
import com.techullurgy.codehorn.domain.code.execution.services.UserFileCreator
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(EndpointConstants.Public.LanguageExecution.POST_CODE_EXECUTION_JAVASCRIPT)
class JavascriptExecutionController {

    @Autowired
    private lateinit var codeExecutionServiceProvider: ObjectProvider<CodeExecutionService>

    @PostMapping
    fun executeJavascriptCode(
        @RequestBody request: CodeExecutionRequest
    ): ResponseEntity<CodeExecutionResultResponse> {

        val codeExecutionService = codeExecutionServiceProvider.getObject(request.submissionId)

        val results = UserFileCreator(request.submissionId, "javascript").use {
            codeExecutionService.executeFor(
                folder = it.file,
                fileContent = request.fileContent,
                testcases = request.testcases
            )
        }

        val response = CodeExecutionResultResponse(
            submissionId = request.submissionId,
            testcaseResults = results
        )

        return ResponseEntity.ok(response)
    }
}