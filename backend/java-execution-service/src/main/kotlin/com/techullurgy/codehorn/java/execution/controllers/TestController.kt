package com.techullurgy.codehorn.java.execution.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/debug")
class TestController {

    @Value("\${consul.host}")
    private lateinit var consul: String

    @GetMapping("/test/consul")
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok(consul)
    }
}