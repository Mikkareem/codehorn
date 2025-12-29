package com.techullurgy.codehorn.cpp.execution

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.techullurgy.codehorn"])
//@EnableDiscoveryClient
class CodeHornCppExecutionApplication

fun main(args: Array<String>) {
    runApplication<CodeHornCppExecutionApplication>(*args)
}