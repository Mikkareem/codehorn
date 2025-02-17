package com.techullurgy.codehorn.submissions.services

import com.techullurgy.codehorn.common.constants.ConsulConstants
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class SubmissionService(
    private val restClient: RestClient
) {
    fun getProblemDetails(problemId: String) {
        restClient.get()
            .uri("http://${ConsulConstants.ServiceNames.PROBLEMS_SERVICE}/")
            .retrieve()
            .toEntity(String::class.java)
    }
}