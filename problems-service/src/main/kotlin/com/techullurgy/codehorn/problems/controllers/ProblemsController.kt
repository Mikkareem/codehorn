package com.techullurgy.codehorn.problems.controllers

import com.techullurgy.codehorn.common.annotations.InternalRestApi
import com.techullurgy.codehorn.common.constants.EndpointConstants
import com.techullurgy.codehorn.common.responses.ProblemByIdForUserResponse
import com.techullurgy.codehorn.common.responses.ProblemByIdResponse
import com.techullurgy.codehorn.problems.services.ProblemsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
    fun problemByIdForUser(@PathVariable("id") problemId: String): ResponseEntity<ProblemByIdForUserResponse> {
        val problem = problemsService.getProblemById(problemId)

        return ResponseEntity.notFound().build<ProblemByIdForUserResponse>()
    }
}