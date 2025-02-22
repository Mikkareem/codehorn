package com.techullurgy.codehorn.gateway.controllers.advices

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AuthException::class)
    fun authException(): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatusCode.valueOf(HttpServletResponse.SC_UNAUTHORIZED)).build()
    }
}

class AuthException: RuntimeException()