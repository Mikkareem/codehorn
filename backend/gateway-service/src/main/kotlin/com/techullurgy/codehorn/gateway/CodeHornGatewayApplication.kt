package com.techullurgy.codehorn.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CodeHornGatewayApplication

fun main(args: Array<String>) {
    runApplication<CodeHornGatewayApplication>(*args)
}