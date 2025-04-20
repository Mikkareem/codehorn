package com.techullurgy.codehorn.cpp.execution.domain

import com.techullurgy.codehorn.domain.code.execution.services.Compiler
import com.techullurgy.codehorn.domain.code.execution.services.ExecutionParametersProvider
import org.springframework.stereotype.Component

@Component
class CppExecutionParametersProvider: ExecutionParametersProvider {
    override fun getCodeFileName() = Compiler.CPP_INPUT_FILE_NAME

    override fun getLanguage() = "cpp"

    override fun getCompiler() = Compiler.FROM_DOCKER_IMAGE_FOR_CPP_COMPILER
}