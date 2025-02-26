package com.techullurgy.codehorn.gateway.controllers

import com.techullurgy.codehorn.common.constants.EndpointConstants
import com.techullurgy.codehorn.common.responses.ProblemByIdForUserResponse
import com.techullurgy.codehorn.gateway.services.ProblemsService
import com.techullurgy.codehorn.gateway.utils.getCurrentAuthenticatedUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
}