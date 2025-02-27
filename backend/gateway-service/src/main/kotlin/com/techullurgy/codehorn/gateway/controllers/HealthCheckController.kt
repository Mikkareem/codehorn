package com.techullurgy.codehorn.gateway.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health-check")
class HealthCheckController {

    @GetMapping
    fun healthCheck(): Map<String, String> {
        return mapOf("status" to "UP")
    }
}