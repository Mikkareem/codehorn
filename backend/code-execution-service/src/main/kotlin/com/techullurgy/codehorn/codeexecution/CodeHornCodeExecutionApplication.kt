package com.techullurgy.codehorn.codeexecution

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
@ComponentScan(basePackages = ["com.techullurgy.codehorn"])
class CodeHornCodeExecutionApplication

fun main(args: Array<String>) {
    runApplication<CodeHornCodeExecutionApplication>(*args)
}