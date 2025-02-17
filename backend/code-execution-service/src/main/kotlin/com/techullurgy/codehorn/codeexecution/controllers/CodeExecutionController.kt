package com.techullurgy.codehorn.codeexecution.controllers

import com.techullurgy.codehorn.common.requests.CodeExecutionRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient

@RestController
@RequestMapping("/code/execution")
class CodeExecutionController {

    private val restClient: RestClient = RestClient.builder()
        .baseUrl("http://localhost:8081")
        .build()


    @PostMapping
    fun test() {
        val response = restClient
            .post()
            .uri("/code/execution/java")
            .body(
                CodeExecutionRequest(
                    submissionId = "9a0s9das9",
                    fileContent = "JAVA_CODE_HERE",
                    sampleTestcases = listOf(
//                        TestcaseDTO("190"),
//                        TestcaseDTO("39"),
//                        TestcaseDTO("46"),
                    ),
                    hiddenTestcases = listOf(
//                        TestcaseDTO("9"),
//                        TestcaseDTO("61"),
//                        TestcaseDTO("65"),
                    ),
                )
            )
            .retrieve()
            .toEntity(String::class.java)
    }
}