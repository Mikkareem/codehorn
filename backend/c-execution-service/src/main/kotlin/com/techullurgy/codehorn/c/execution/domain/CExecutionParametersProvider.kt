package com.techullurgy.codehorn.c.execution.domain

import com.techullurgy.codehorn.domain.code.execution.services.Compiler
import com.techullurgy.codehorn.domain.code.execution.services.ExecutionParametersProvider
import org.springframework.stereotype.Component

@Component
class CExecutionParametersProvider: ExecutionParametersProvider {
    override fun getCodeFileName() = Compiler.C_INPUT_FILE_NAME

    override fun getLanguage() = "c"

    override fun getCompiler() = Compiler.FROM_DOCKER_IMAGE_FOR_C_COMPILER
}