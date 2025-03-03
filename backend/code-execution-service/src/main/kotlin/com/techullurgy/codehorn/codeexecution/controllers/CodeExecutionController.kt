package com.techullurgy.codehorn.codeexecution.controllers

import com.techullurgy.codehorn.codeexecution.services.OverallCodeExecutionService
import com.techullurgy.codehorn.common.constants.EndpointConstants
import com.techullurgy.codehorn.common.constants.getProblemByIdForCodeExecution
import com.techullurgy.codehorn.common.requests.CodeRequest
import com.techullurgy.codehorn.common.responses.ProblemByIdResponse
import com.techullurgy.codehorn.common.responses.RunResultResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient

@RestController
class CodeExecutionController(
    private val service: OverallCodeExecutionService
) {

    @PostMapping(EndpointConstants.Public.CodeExecution.POST_RUN_CODE_BY_USER)
    fun runCode(
        @RequestBody request: CodeRequest,
        @PathVariable("id") problemId: String,
        @RequestParam(value = "userId", required = true) userId: String
    ): ResponseEntity<RunResultResponse> {
        return service.runCode(userId, problemId, request)
    }

    @PostMapping(EndpointConstants.Public.CodeExecution.POST_SUBMIT_CODE_BY_USER)
    fun submitCode(
        @RequestBody request: CodeRequest,
        @PathVariable("id") problemId: String,
        @RequestParam(value = "userId", required = true) userId: String
    ) {

    }
}