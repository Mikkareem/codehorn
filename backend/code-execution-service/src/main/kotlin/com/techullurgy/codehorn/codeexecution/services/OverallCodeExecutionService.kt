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

        var filledFileContent = """
            ${AppConstants.CODEHORN_IMPORTS_REPLACEMENT_STRING}
            
            ${AppConstants.CODEHORN_UTILS_REPLACEMENT_STRING}
            
            ${AppConstants.CODEHORN_ORIGINAL_REPLACEMENT_STRING}
            
            ${AppConstants.CODEHORN_CODE_REPLACEMENT_STRING}
            
            ${AppConstants.CODEHORN_MAIN_REPLACEMENT_STRING}
            
        """.trimIndent()

        logger.debug("Filled file content: {}", filledFileContent)

        val languageImports = when(body.language) {
            "c" -> problem.fileContent.cImports
            "cpp" -> problem.fileContent.cppImports
            "java" -> problem.fileContent.javaImports
            "python" -> problem.fileContent.pythonImports
            "javascript" -> problem.fileContent.javascriptImports
            else -> TODO()
        }

        val languageOriginalSolution = when(body.language) {
            "c" -> problem.fileContent.c
            "cpp" -> problem.fileContent.cpp
            "java" -> problem.fileContent.java
            "python" -> problem.fileContent.python
            "javascript" -> problem.fileContent.javascript
            else -> TODO()
        }

        val languageUtils = when(body.language) {
            "c" -> problem.fileContent.cUtils
            "cpp" -> problem.fileContent.cppUtils
            "java" -> problem.fileContent.javaUtils
            "python" -> problem.fileContent.pythonUtils
            "javascript" -> problem.fileContent.javascriptUtils
            else -> TODO()
        }

        val languageMain = when(body.language) {
            "c" -> problem.fileContent.cMain
            "cpp" -> problem.fileContent.cppMain
            "java" -> problem.fileContent.javaMain
            "python" -> problem.fileContent.pythonMain
            "javascript" -> problem.fileContent.javascriptMain
            else -> TODO()
        }

        filledFileContent = filledFileContent.replace(AppConstants.CODEHORN_IMPORTS_REPLACEMENT_STRING, languageImports)
        logger.debug("Filled File Content after imports replacement: {}", filledFileContent)
        filledFileContent = filledFileContent.replace(AppConstants.CODEHORN_UTILS_REPLACEMENT_STRING, languageUtils)
        logger.debug("Filled File Content after imports replacement: {}", filledFileContent)
        filledFileContent = filledFileContent.replace(AppConstants.CODEHORN_ORIGINAL_REPLACEMENT_STRING, languageOriginalSolution)
        logger.debug("Filled File Content after imports replacement: {}", filledFileContent)
        filledFileContent = filledFileContent.replace(AppConstants.CODEHORN_CODE_REPLACEMENT_STRING, body.userCode)
        logger.debug("Filled File Content after imports replacement: {}", filledFileContent)
        filledFileContent = filledFileContent.replace(AppConstants.CODEHORN_MAIN_REPLACEMENT_STRING, languageMain)
        logger.debug("Filled File Content after imports replacement: {}", filledFileContent)

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