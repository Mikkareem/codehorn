package com.techullurgy.codehorn.java.execution

import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.common.model.TestcaseCollectionType
import com.techullurgy.codehorn.common.model.TestcaseDataType
import com.techullurgy.codehorn.common.model.TestcaseType
import com.techullurgy.codehorn.domain.code.execution.services.CodeExecutionService
import com.techullurgy.codehorn.domain.code.execution.services.CodeExecutionType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan
import java.io.File
import kotlin.random.Random

@SpringBootApplication
@ComponentScan(basePackages = ["com.techullurgy.codehorn"])
@EnableDiscoveryClient
class CodeHornJavaExecutionApplication

fun main(args: Array<String>) {
    runApplication<CodeHornJavaExecutionApplication>(*args)
}