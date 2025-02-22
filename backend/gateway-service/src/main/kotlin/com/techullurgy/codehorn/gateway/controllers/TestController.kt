package com.techullurgy.codehorn.gateway.controllers

import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    data class RoleResp(val roles: List<String>)

    @GetMapping("/hello")
    fun hello(): ResponseEntity<String> {
        val authentication = SecurityContextHolder.getContext().authentication
        val user = authentication.name
        return ResponseEntity.ok("Hello World $user")
    }

    @GetMapping("/roles")
    fun roles(): ResponseEntity<RoleResp> {

        val authorities = SecurityContextHolder.getContext().authentication.authorities.toList()

        val response = RoleResp(authorities.map { it.toString() })

        println(response)

        return ResponseEntity.ok(response)
    }
}