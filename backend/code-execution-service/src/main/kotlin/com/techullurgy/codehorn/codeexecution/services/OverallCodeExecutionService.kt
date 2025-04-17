package com.techullurgy.codehorn.codeexecution.services

import com.techullurgy.codehorn.common.constants.*
import com.techullurgy.codehorn.common.web.mappers.toTestcaseResultDTO
import com.techullurgy.codehorn.common.web.requests.CodeExecutionRequest
import com.techullurgy.codehorn.common.web.requests.CodeRequest
import com.techullurgy.codehorn.common.web.responses.CodeExecutionResultResponse
import com.techullurgy.codehorn.common.web.responses.ProblemByIdResponse
import com.techullurgy.codehorn.common.web.responses.RunResultResponse
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class OverallCodeExecutionService(
    private val restClient: RestClient
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun runCode(
        userId: String,
        problemId: String,
        body: CodeRequest
    ): ResponseEntity<RunResultResponse> {
        val problemResponse = restClient.get()
            .uri(EndpointConstants.Internal.Problems.getProblemByIdForCodeExecution(userId, problemId))
            .retrieve()
            .toEntity(ProblemByIdResponse::class.java)

        val problem = problemResponse.body!!

        val languageFileContent = when(body.language) {
            "c" -> problem.fileContent.c
            "cpp" -> problem.fileContent.cpp
            "java" -> problem.fileContent.java
            "python" -> problem.fileContent.python
            "javascript" -> problem.fileContent.javascript
            else -> TODO()
        }

        val languageReplaceStringInFileContent = when(body.language) {
            "c" -> problem.fileContent.creplaceStr
            "cpp" -> problem.fileContent.cppReplaceStr
            "java" -> problem.fileContent.javaReplaceStr
            "python" -> problem.fileContent.pythonReplaceStr
            "javascript" -> problem.fileContent.javascriptReplaceStr
            else -> TODO()
        }

        val filledFileContent = languageFileContent.replace(languageReplaceStringInFileContent, body.userCode)

        val requestBody = CodeExecutionRequest(
            submissionId = userId,
            fileContent = filledFileContent,
            testcases = problem.testcases
        )

        logger.debug("Code Execution Request Body: {}", requestBody)

        val endpointUri = when(body.language) {
            "c" -> EndpointConstants.Public.LanguageExecution.postCodeExecutionC()
            "cpp" -> EndpointConstants.Public.LanguageExecution.postCodeExecutionCpp()
            "java" -> EndpointConstants.Public.LanguageExecution.postCodeExecutionJava()
            "python" -> EndpointConstants.Public.LanguageExecution.postCodeExecutionPython()
            "javascript" -> EndpointConstants.Public.LanguageExecution.postCodeExecutionJavascript()
            else -> TODO()
        }

        val codeExecutionResultResponse = restClient.post()
            .uri(endpointUri)
            .contentType(MediaType.APPLICATION_JSON)
            .body(requestBody)
            .retrieve()
            .toEntity(CodeExecutionResultResponse::class.java)

        logger.debug("Code Execution Response: {}", codeExecutionResultResponse)

        val response = RunResultResponse(
            problemId = problemId,
            results = codeExecutionResultResponse.body!!.testcaseResults.map { it.toTestcaseResultDTO() }
        )

        return ResponseEntity.ok(response)
    }
}