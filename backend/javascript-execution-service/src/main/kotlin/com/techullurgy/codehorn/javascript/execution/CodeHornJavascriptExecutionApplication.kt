package com.techullurgy.codehorn.javascript.execution

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.techullurgy.codehorn"])
class CodeHornJavascriptExecutionApplication

fun main(args: Array<String>) {
    runApplication<CodeHornJavascriptExecutionApplication>(*args)
}