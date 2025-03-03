package com.techullurgy.codehorn.codeexecution.services

import com.techullurgy.codehorn.common.constants.*
import com.techullurgy.codehorn.common.requests.CodeExecutionRequest
import com.techullurgy.codehorn.common.requests.CodeRequest
import com.techullurgy.codehorn.common.responses.CodeExecutionResultResponse
import com.techullurgy.codehorn.common.responses.ProblemByIdResponse
import com.techullurgy.codehorn.common.responses.RunResultResponse
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class OverallCodeExecutionService(
    private val restClient: RestClient
) {
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
            sampleTestcases = problem.testcases.filter { !it.isHidden },
            hiddenTestcases = problem.testcases.filter { it.isHidden }
        )

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

        val response = RunResultResponse(
            problemId = problemId,
            results = codeExecutionResultResponse.body!!.testcaseResults
        )

        return ResponseEntity.ok(response)
    }
}