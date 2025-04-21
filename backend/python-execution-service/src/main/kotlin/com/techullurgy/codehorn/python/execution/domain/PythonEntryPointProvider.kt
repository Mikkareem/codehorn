package com.techullurgy.codehorn.python.execution.domain

import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.domain.code.execution.services.Compiler
import com.techullurgy.codehorn.domain.code.execution.services.EntryPointProvider
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class PythonEntryPointProvider(
    private val testcases: List<ProblemTestcase>
): EntryPointProvider {

    override fun getSampleIds(): List<String> = testcases.filter { !it.isHidden }.map { it.id }

    override fun getHiddenIds(): List<String> = testcases.filter { it.isHidden }.map { it.id }

    override fun getRunCommand(): String = "python3 ${Compiler.PYTHON_INPUT_FILE_NAME}"

    override fun getCompilationCommand(): String? = null
}