package com.techullurgy.codehorn.common.web.controllers

import com.techullurgy.codehorn.common.constants.EndpointConstants
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(EndpointConstants.Internal.HEALTH_CHECK_FOR_CONSUL)
class HealthCheckController {

    @GetMapping
    fun healthCheck(): Map<String, String> {
        return mapOf("status" to "UP")
    }
}