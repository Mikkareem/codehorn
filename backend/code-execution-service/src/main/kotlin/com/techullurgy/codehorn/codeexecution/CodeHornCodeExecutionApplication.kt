package com.techullurgy.codehorn.codeexecution

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class CodeHornCodeExecutionApplication

fun main(args: Array<String>) {
    runApplication<CodeHornCodeExecutionApplication>(*args)
}