package com.techullurgy.codehorn.javascript.execution

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.techullurgy.codehorn"])
@EnableDiscoveryClient
class CodeHornJavascriptExecutionApplication

fun main(args: Array<String>) {
    runApplication<CodeHornJavascriptExecutionApplication>(*args)
}