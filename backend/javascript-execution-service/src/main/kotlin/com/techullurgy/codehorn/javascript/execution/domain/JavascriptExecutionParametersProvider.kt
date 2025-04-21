package com.techullurgy.codehorn.javascript.execution.domain

import com.techullurgy.codehorn.domain.code.execution.services.Compiler
import com.techullurgy.codehorn.domain.code.execution.services.ExecutionParametersProvider
import org.springframework.stereotype.Component

@Component
class JavascriptExecutionParametersProvider: ExecutionParametersProvider {
    override fun getCodeFileName() = Compiler.JAVASCRIPT_INPUT_FILE_NAME

    override fun getLanguage() = "javascript"

    override fun getCompiler() = Compiler.FROM_DOCKER_IMAGE_FOR_JAVASCRIPT_COMPILER
}