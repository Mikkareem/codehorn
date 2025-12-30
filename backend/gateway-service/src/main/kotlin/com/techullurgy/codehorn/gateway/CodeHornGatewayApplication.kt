package com.techullurgy.codehorn.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.techullurgy.codehorn"])
class CodeHornGatewayApplication

fun main(args: Array<String>) {
    runApplication<CodeHornGatewayApplication>(*args)
}