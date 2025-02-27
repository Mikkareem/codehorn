package com.techullurgy.codehorn.gateway.controllers

import com.techullurgy.codehorn.common.constants.EndpointConstants
import com.techullurgy.codehorn.common.responses.ProblemByIdForUserResponse
import com.techullurgy.codehorn.common.responses.SnippetForProblemForLanguageResponse
import com.techullurgy.codehorn.gateway.services.ProblemsService
import com.techullurgy.codehorn.gateway.utils.getCurrentAuthenticatedUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProblemsController(
    private val problemsService: ProblemsService
) {

    @GetMapping(EndpointConstants.Public.Problems.GET_PROBLEM_BY_ID_FOR_USER)
    fun getAllProblemsList(@PathVariable("id") problemId: String): ResponseEntity<ProblemByIdForUserResponse> {
        val currentUser = getCurrentAuthenticatedUser()
        return problemsService.getProblemByIdForUser(userId = currentUser, problemId = problemId)
    }

    @GetMapping(EndpointConstants.Public.Problems.GET_SNIPPET_FOR_PROBLEM_FOR_LANGUAGE)
    fun getSnippetForProblemForLanguage(
        @PathVariable("id") problemId: String,
        @RequestParam("language") language: String
    ): ResponseEntity<SnippetForProblemForLanguageResponse> {
        val currentUser = getCurrentAuthenticatedUser()
        return problemsService.getSnippetForProblem(userId = currentUser, problemId = problemId, language = language)
    }
}