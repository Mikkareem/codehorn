package com.techullurgy.codehorn.contests

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.techullurgy.codehorn"])
class CodeHornContestsApplication

fun main(args: Array<String>) {
    runApplication<CodeHornContestsApplication>(*args)
}