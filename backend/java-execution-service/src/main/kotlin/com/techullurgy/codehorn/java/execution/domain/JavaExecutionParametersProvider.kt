package com.techullurgy.codehorn.java.execution.domain

import com.techullurgy.codehorn.domain.code.execution.services.Compiler
import com.techullurgy.codehorn.domain.code.execution.services.ExecutionParametersProvider
import org.springframework.stereotype.Component

@Component
class JavaExecutionParametersProvider: ExecutionParametersProvider {
    override fun getCodeFileName() = Compiler.JAVA_INPUT_FILE_NAME

    override fun getLanguage() = "java"

    override fun getCompiler() = Compiler.FROM_DOCKER_IMAGE_FOR_JAVA_COMPILER

    override fun provide() = """|
        |RUN javac ${getCodeFileName()}
        |CMD java ${getCodeFileName().substringBefore('.')} $${"testcase_no"}
    |"""
}