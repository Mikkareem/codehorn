package com.techullurgy.codehorn.java.execution.domain

import com.techullurgy.codehorn.common.model.ProblemTestcase
import com.techullurgy.codehorn.domain.code.execution.services.Compiler
import com.techullurgy.codehorn.domain.code.execution.services.EntryPointProvider
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class JavaEntryPointProvider(
    private val testcases: List<ProblemTestcase>
): EntryPointProvider {

    override fun getSampleIds(): List<String> = testcases.filter { !it.isHidden }.map { it.id }

    override fun getHiddenIds(): List<String> = testcases.filter { it.isHidden }.map { it.id }

    override fun getRunCommand(): String = "java ${Compiler.JAVA_INPUT_FILE_NAME.replace(".java", "")}"

    override fun getCompilationCommand(): String? = "javac -nowarn ${Compiler.JAVA_INPUT_FILE_NAME}"
}