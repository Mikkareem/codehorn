package com.techullurgy.codehorn.common.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.techullurgy.codehorn.common.web.exceptions.CodehornRestClientException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

data class FailureResponse(
    val message: String
)

@RestControllerAdvice
class CommonRestControllerAdvice(
    private val objectMapper: ObjectMapper
) {

    @ExceptionHandler(CodehornRestClientException::class)
    fun handleCodehornRestClientException(ex: CodehornRestClientException): ResponseEntity<FailureResponse> {

        val errorResponse = try {
            objectMapper.readValue(ex.exceptionBody, FailureResponse::class.java)
        } catch(_: Exception) {
            FailureResponse(
                message = ex.exceptionBody,
            )
        }

        return ResponseEntity.status(ex.status.value()).body(errorResponse)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<FailureResponse> {
        val errorResponse = FailureResponse(
            message = ex.message ?: "Unknown error occurred",
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
}