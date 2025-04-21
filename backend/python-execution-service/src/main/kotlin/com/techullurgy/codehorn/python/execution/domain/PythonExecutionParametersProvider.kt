package com.techullurgy.codehorn.python.execution.domain

import com.techullurgy.codehorn.domain.code.execution.services.Compiler
import com.techullurgy.codehorn.domain.code.execution.services.ExecutionParametersProvider
import org.springframework.stereotype.Component

@Component
class PythonExecutionParametersProvider: ExecutionParametersProvider {
    override fun getCodeFileName() = Compiler.PYTHON_INPUT_FILE_NAME

    override fun getLanguage() = "python"

    override fun getCompiler() = Compiler.FROM_DOCKER_IMAGE_FOR_PYTHON3_COMPILER
}