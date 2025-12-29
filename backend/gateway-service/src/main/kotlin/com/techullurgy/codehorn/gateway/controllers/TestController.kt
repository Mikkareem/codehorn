package com.techullurgy.codehorn.gateway.controllers

import com.techullurgy.codehorn.common.constants.ConsulConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient

@RestController
class TestController {

    @Autowired
    private lateinit var restClient: RestClient

    data class RoleResp(val roles: List<String>)

    @GetMapping("/hello")
    fun hello(): ResponseEntity<String> {
        val authentication = SecurityContextHolder.getContext().authentication
        val user = authentication?.name
        return ResponseEntity.ok("Hello World $user")
    }

    @GetMapping("/roles")
    fun roles(): ResponseEntity<RoleResp> {

        val authorities = SecurityContextHolder.getContext().authentication?.authorities?.toList()

        val response = RoleResp(authorities?.map { it.toString() } ?: emptyList())

        println(response)

        return ResponseEntity.ok(response)
    }

    @GetMapping("/test/a")
    fun test1(): ResponseEntity<String> {
        return restClient.get()
            .uri("http://${ConsulConstants.ServiceNames.PROBLEMS_SERVICE}/test")
            .retrieve()
            .toEntity(String::class.java)
    }
}