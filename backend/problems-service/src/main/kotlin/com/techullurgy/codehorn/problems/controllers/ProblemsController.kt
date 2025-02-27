package com.techullurgy.codehorn.problems.controllers

import com.techullurgy.codehorn.common.annotations.InternalRestApi
import com.techullurgy.codehorn.common.constants.EndpointConstants
import com.techullurgy.codehorn.common.dto.ProblemDTO
import com.techullurgy.codehorn.common.responses.ProblemByIdForUserResponse
import com.techullurgy.codehorn.common.responses.ProblemByIdResponse
import com.techullurgy.codehorn.common.responses.SnippetForProblemForLanguageResponse
import com.techullurgy.codehorn.problems.data.mappers.toTestcaseDTO
import com.techullurgy.codehorn.problems.services.ProblemsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ProblemsController(
    private val problemsService: ProblemsService
) {
    @InternalRestApi
    @GetMapping(EndpointConstants.Internal.Problems.GET_PROBLEM_BY_ID_FOR_CODE_EXECUTION)
    fun problemByIdForExecution(@PathVariable("id") problemId: String): ResponseEntity<ProblemByIdResponse> {
        val problem = problemsService.getProblemById(problemId)

        return ResponseEntity.notFound().build<ProblemByIdResponse>()
    }

    @GetMapping(EndpointConstants.Public.Problems.GET_PROBLEM_BY_ID_FOR_USER)
    fun problemByIdForUser(
        @PathVariable("id") problemId: String,
        @RequestParam("userId") userId: String
    ): ResponseEntity<ProblemByIdForUserResponse> {
//        val problem = problemsService.getProblemById(problemId)

        val problem = problemsService.getAllProblems().first()

        val problemDTO = ProblemDTO(
            problemNo = problemId.toInt(),
            title = problem.title,
            difficulty = problem.difficulty,
            description = problem.description,
            preferredSnippet = problem.snippet.java,
            preferredLanguage = "java",
            sampleTestcases = problem.testcases.filter { !it.isHidden }.map { it.toTestcaseDTO() }
        )

        val response = ProblemByIdForUserResponse(problemDTO)

        return ResponseEntity.ok(response)
    }

    @GetMapping(EndpointConstants.Public.Problems.GET_SNIPPET_FOR_PROBLEM_FOR_LANGUAGE)
    fun snippetForProblemForLanguage(
        @PathVariable("id") problemId: String,
        @RequestParam("userId") userId: String,
        @RequestParam(value = "language", required = true) language: String
    ): ResponseEntity<SnippetForProblemForLanguageResponse> {
        val problem = problemsService.getAllProblems().first()

        val desiredSnippet = when(language) {
            "c" -> problem.snippet.c
            "cpp" -> problem.snippet.cpp
            "java" -> problem.snippet.java
            "python" -> problem.snippet.python
            "javascript" -> problem.snippet.javascript
            else -> TODO()
        }

        val response = SnippetForProblemForLanguageResponse(
            language = language,
            snippet = desiredSnippet
        )

        return ResponseEntity.ok(response)
    }
}