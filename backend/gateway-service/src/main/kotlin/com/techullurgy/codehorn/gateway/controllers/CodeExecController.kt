package com.techullurgy.codehorn.gateway.controllers

import com.techullurgy.codehorn.common.constants.EndpointConstants
import com.techullurgy.codehorn.common.constants.postRunCodeByUser
import com.techullurgy.codehorn.common.constants.postSubmitCodeByUser
import com.techullurgy.codehorn.common.requests.CodeRequest
import com.techullurgy.codehorn.common.responses.RunResultResponse
import com.techullurgy.codehorn.gateway.utils.getCurrentAuthenticatedUser
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient

@RestController
class CodeExecController(
    private val restClient: RestClient
) {

    @PostMapping(EndpointConstants.Public.CodeExecution.POST_RUN_CODE_BY_USER)
    fun runCode(
        @RequestBody request: CodeRequest,
        @PathVariable("id") problemId: String,
    ): ResponseEntity<RunResultResponse> {
        val currentUser = getCurrentAuthenticatedUser()

        return restClient.post()
            .uri(EndpointConstants.Public.CodeExecution.postRunCodeByUser(currentUser, problemId))
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
            .toEntity(RunResultResponse::class.java)
    }

    @PostMapping(EndpointConstants.Public.CodeExecution.POST_SUBMIT_CODE_BY_USER)
    fun submitCode(
        @RequestBody request: CodeRequest,
        @PathVariable("id") problemId: String,
    ) {
        val currentUser = getCurrentAuthenticatedUser()

        restClient.post()
            .uri(EndpointConstants.Public.CodeExecution.postSubmitCodeByUser(currentUser, problemId))
            .contentType(MediaType.APPLICATION_JSON)
            .body(request)
            .retrieve()
//            .toEntity(RunResultResponse::class.java)
    }
}