package com.techullurgy.codehorn.gateway.services

import com.techullurgy.codehorn.common.constants.EndpointConstants
import com.techullurgy.codehorn.common.constants.getProblemByIdForUserUri
import com.techullurgy.codehorn.common.constants.getSnippetForProblemForLanguageUri
import com.techullurgy.codehorn.common.responses.ProblemByIdForUserResponse
import com.techullurgy.codehorn.common.responses.SnippetForProblemForLanguageResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class ProblemsService(
    private val client: RestClient
) {
    fun getProblemByIdForUser(userId: String, problemId: String): ResponseEntity<ProblemByIdForUserResponse> {
        return client.get()
            .uri(EndpointConstants.Public.Problems.getProblemByIdForUserUri(userId, problemId))
            .retrieve()
            .toEntity(ProblemByIdForUserResponse::class.java)
    }

    fun getSnippetForProblem(
        userId: String,
        problemId: String,
        language: String
    ): ResponseEntity<SnippetForProblemForLanguageResponse> {
        return client.get()
            .uri(EndpointConstants.Public.Problems.getSnippetForProblemForLanguageUri(userId, problemId, language))
            .retrieve()
            .toEntity(SnippetForProblemForLanguageResponse::class.java)
    }
}