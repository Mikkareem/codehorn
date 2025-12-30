package com.techullurgy.codehorn.submissions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.techullurgy.codehorn"])
class CodeHornSubmissionsApplication

fun main(args: Array<String>) {
    runApplication<CodeHornSubmissionsApplication>(*args)
}