package com.techullurgy.codehorn.python.execution

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CodeHornPythonExecutionApplication

fun main(args: Array<String>) {
    runApplication<CodeHornPythonExecutionApplication>(*args)
}