package com.techullurgy.codehorn.python.execution

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.techullurgy.codehorn"])
@EnableDiscoveryClient
class CodeHornPythonExecutionApplication

fun main(args: Array<String>) {
    runApplication<CodeHornPythonExecutionApplication>(*args)
}