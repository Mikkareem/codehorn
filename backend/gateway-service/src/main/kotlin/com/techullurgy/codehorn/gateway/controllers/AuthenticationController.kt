package com.techullurgy.codehorn.gateway.controllers

import com.techullurgy.codehorn.gateway.request.AuthLoginRequest
import com.techullurgy.codehorn.gateway.request.AuthLoginResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthenticationController {

    @PostMapping("/login")
    fun login(@RequestBody request: AuthLoginRequest): ResponseEntity<AuthLoginResponse> {

        println("Login called with $request")

        if(request.username == "Irsath" && request.password == "1234") {
            return ResponseEntity.ok(AuthLoginResponse(status = "SUCCESS", token = "12345678"))
        }

        return ResponseEntity.ok(AuthLoginResponse(status = "FAILURE", error = "Validation Message"))
    }
}